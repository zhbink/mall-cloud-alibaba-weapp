var app = getApp();
var api = require('../../config/api.js');
var util = require('../../utils/util.js');
var websocket = require('../../services/websocket.js');

Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo: app.globalData.userInfo,
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    load: false
  },

  startLogin: function(e) {
    console.log("startLogin携带的内容：");
    console.log(e);
    this.setData({
      load: true
    })
    util.backendLogin(e.detail).then((userInfo) => {
      this.setData({
        userInfo: userInfo,
        hasUserInfo: true
      })
      websocket.wsConnect()
    });
  },

  goback: function() {
    wx.navigateBack({
      delta: 1
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }
})