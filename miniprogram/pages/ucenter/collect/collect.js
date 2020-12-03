var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');

var app = getApp();

Page({
  data: {
    collectList: [],
    page: 1,
    size: 10
  },
  getCollectList() {
    let that = this;
    util.request(api.CollectList, {
      page: this.data.page,
      size: this.data.size
    }).then(function (res) {
      if (res.errno === 0) {
        console.log(res.data);
        that.setData({
          collectList: that.data.collectList.concat(res.data),
        });
      }
    });
  },
  onLoad: function (options) {
    this.getCollectList();
  },
  onReady: function () {

  },
  onShow: function () {

  },
  onHide: function () {
    // 页面隐藏

  },
  onUnload: function () {
    // 页面关闭
  },
  openGoods(event) {
    
    let that = this;
    let goodsId = this.data.collectList[event.currentTarget.dataset.index].id;

    
    //触摸时间距离页面打开的毫秒数  
    var touchTime = that.data.touch_end - that.data.touch_start;
    console.log(touchTime);
    //如果按下时间大于350为长按  
    if (touchTime > 350) {
      wx.showModal({
        title: '',
        content: '确定删除吗？',
        success: function (res) {
          if (res.confirm) {
            
            util.request(api.CollectAddOrDelete + '/' + goodsId + '/' + true, {}, "POST").then(function (res) {
              if (res.errno === 0) {
                console.log(res.data);
                wx.showToast({
                  title: '删除成功',
                  icon: 'success',
                  duration: 2000
                });
                that.getCollectList();
              }
            });
          }
        }
      })
    } else {
      
      wx.navigateTo({
        url: '/pages/goods/goods?id=' + goodsId,
      });
    }  
  },
  //按下事件开始  
  touchStart: function (e) {
    let that = this;
    that.setData({
      touch_start: e.timeStamp
    })
    console.log(e.timeStamp + '- touch-start')
  },
  //按下事件结束  
  touchEnd: function (e) {
    let that = this;
    that.setData({
      touch_end: e.timeStamp
    })
    console.log(e.timeStamp + '- touch-end')
  }, 
  onReachBottom: function () {
    console.log("拉到底")
    this.setData({
      page: this.data.page + 1
    })
    this.getCollectList()
  },
})