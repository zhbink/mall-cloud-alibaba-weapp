var api = require('../config/api.js');

function formatTime(date) {
  var year = date.getFullYear()
  var month = date.getMonth() + 1
  var day = date.getDate()

  var hour = date.getHours()
  var minute = date.getMinutes()
  var second = date.getSeconds()


  return [year, month, day].map(formatNumber).join('-') + ' ' + [hour, minute].map(formatNumber).join(':')
}

function formatNumber(n) {
  n = n.toString()
  return n[1] ? n : '0' + n;
}

/**
 * 封封微信的的request
 */
function request(url, data = {}, method = "GET") {
  let that = this
  let token = wx.getStorageSync('token')

  return new Promise(function(resolve, reject) {
    wx.request({
      url: url,
      data: data,
      method: method,
      header: {
        'Content-Type': 'application/json',
        'Authorization': token
      },
      success: function(res) {
        if (res.statusCode == 200) {
          if (res.data.errno == 3002 || res.data.errno == 3003 || res.data.errno == 3004 || res.data.errno == 3005) {
            console.log(res.data.errmsg)
            //TOKEN_IS_EMPTY
            //需要登录后才可以操作
            wx.getSetting({
              success: res => {
                if (res.authSetting['scope.userInfo']) {
                  // // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
                  that.getUserInfo().then((res) => {
                    that.backendLogin(res).then((res) => {
                      that.request(url, data, method)
                      console.log('再次请求')
                    })
                  })

                } else {
                  wx.navigateTo({
                    url: '/pages/auth/auth'
                  })
                }
              }
            })

          } else {
            resolve(res.data);
          }
        } else {
          reject(res.errMsg);
        }

      },
      fail: function(err) {
        reject(err)
        console.log("failed")
      }
    })
  });
}



/**
 * 检查微信会话是否过期
 */
function checkSession() {
  return new Promise(function(resolve, reject) {
    wx.checkSession({
      success: function() {
        resolve(true);
      },
      fail: function() {
        wx.login() //重新登录
        resolve(true);
      }
    })
  });
}

/**
 * 在后端服务器进行登录
 */
function backendLogin(detail) {
  let that = this;
  let code = null;
  return new Promise(function(resolve, reject) {
    return that.login().then((res) => {
      code = res.code;
    }).then(() => {
      console.log(code);
      console.log(detail);
      //登录远程服务器
      //that.request(api.AuthLoginByWeixin, {
        that.request("api.AuthLoginByWeixin", {
        code: code,
        detail: detail
      }, 'POST').then(res => {
        if (res) {
          
          //存储用户信息
          wx.setStorageSync('userInfo', res);
          wx.setStorageSync('token', res.openId);
          console.log(res);
          resolve(detail.userInfo);
        } else {
          reject(res);
        }
      }).catch((err) => { //request
        reject(err);
      });
    }).catch((err) => { //login
      reject(err);
    })
  });
}

/**
 * 调用微信登录,获取jscode
 */
function login() {
  let that = this
  return new Promise(function(resolve, reject) {
    that.checkSession().then(() => {
      wx.login({
        success: function (res) {
          if (res.code) {
            resolve(res);
          } else {
            reject(res);
          }
        },
        fail: function (err) {
          reject(err);
        }
      });
    })
  });
}

/**
 * 获取userInfo,只有已经授权的用户能使用这个接口
 */
function getUserInfo() {
  return new Promise(function(resolve, reject) {
    wx.getUserInfo({
      withCredentials: true,
      success: function(res) {
        console.log(res)
        resolve(res);
      },
      fail: function(err) {
        reject(err);
      }
    })
  });
}

function showErrorToast(msg) {
  wx.showToast({
    title: msg,
    image: '/static/images/icon_error.png'
  })
}

module.exports = {
  formatTime,
  request,
  showErrorToast,
  checkSession,
  login,
  getUserInfo,
  backendLogin,
}