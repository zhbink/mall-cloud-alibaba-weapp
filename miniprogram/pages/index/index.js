const util = require('../../utils/util.js');
const api = require('../../config/api.js');
const user = require('../../services/user.js');

//获取应用实例
const app = getApp()
Page({
  data: {
    banner: [],
    channel: [],
    indexGoods: [],
    page: 1,
    size: 4,
    total: 4,
  },
  onShareAppMessage: function() {
    return {
      title: '古早交易平台',
      desc: '一款开源仿闲鱼交易平台！',
      path: '/pages/index/index'
    }
  },

  getIndexData: function() {
    let that = this;
    util.request(api.IndexUrl).then(function(res) {
      console.log(res.data)
      if (res.data) {
        that.setData({
          indexGoods: res.data.indexGoodsList.list,
          total: res.data.indexGoodsList.total,
          banner: res.data.banner,
          channel: res.data.channel
        });
      }
    });
  },
  getIndexMore: function() {
    let that = this;
    util.request(api.IndexMore, {
      page: this.data.page,
      size: this.data.size
    }).then(function(res) {
      console.log("当前获取：pageNum"+that.data.page+"pageSize:"+that.data.size);
      if (res) {
        that.setData({
          indexGoods: that.data.indexGoods.concat(res.data.list),
        });
      }
    });
  },
  onLoad: function(options) {

    this.getIndexData();

  },
  onReady: function() {
    // 页面渲染完成
  },
  onShow: function() {
    // 页面显示
  },
  onHide: function() {
    // 页面隐藏
  },
  onUnload: function() {
    // 页面关闭
  },

  onPullDownRefresh: function() {
    console.log("上拉刷新")
    this.onLoad()
    setTimeout(function callback() {
      wx.stopPullDownRefresh()
    }, 500)


  },
  onReachBottom: function() {
    if (this.data.total > this.data.page * this.data.size) {
    console.log("拉到底")
    this.setData({
      page: this.data.page + 1
    })
    this.getIndexMore()
    }
    else{
      console.log("没有数据了");
    }
  },
})