var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');

var app = getApp();

Page({
  data: {
    boughtList: [],
    page: 1,
    size: 10
  },
  getBoughtList() {
    let that = this;
    util.request(api.BoughtList, {
      userId: wx.getStorageSync('userId'),
      page: this.data.page,
      size: this.data.size
    }).then(function(res) {
      if (res) {
        for(let item of res.data) {
          item.soldTime = item.soldTime.substr(0,19).replace('T', ' ');
        }

        that.setData({
          boughtList: that.data.boughtList.concat(res.data),
        });
      }
    });
  },
  onLoad: function(options) {
    this.getBoughtList();
  },
  onReady: function() {

  },
  onShow: function() {

  },
  onHide: function() {
    // 页面隐藏

  },
  onUnload: function() {
    // 页面关闭
  },
  openGoods(event) {
    let goodsId = this.data.boughtList[event.currentTarget.dataset.index].id;

    wx.navigateTo({
      url: '/pages/goods/goods?id=' + goodsId,
    });
  },
  onReachBottom: function() {
    console.log("拉到底")
    this.setData({
      page: this.data.page + 1
    })
    this.getBoughtList()
  },

})