var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');
var websocket = require('../../../services/websocket.js');

var app = getApp();

Page({
  data: {
    chatList: [],
    offsetTime: null,
    size: 10,
  },
  onLoad: function(options) {
    // 页面初始化 options为页面跳转所带来的参数

  },
  onReady: function() {
    // 页面渲染完成

  },
  onShow: function() {
    // 页面显示
    let now = new Date();
    this.setData({
      offsetTime: now.toISOString(),
      chatList: []
    })
    if (wx.getStorageSync('token')) {
      this.getChatList();
      this.openListen();
    }
  },
  onHide: function() {
    // 页面隐藏
    websocket.listenBadge()

  },
  onUnload: function() {
    // 页面关闭

  },
  getChatList: function() {
    let that = this;
    util.request(api.ChatIndex, {
      size: this.data.size,
      offsetTime: this.data.offsetTime
    }).then(function(res) {
      if (res.errno === 0) {
        console.log(res.data);
        that.setData({
          chatList: that.data.chatList.concat(res.data.chats),
          offsetTime: res.data.offsetTime
        });
      } else {
        console.log(res)
      }
    })
  },
  navForm: function(e) {
    var chatId = e.currentTarget.dataset.id
    var index = e.currentTarget.dataset.index
    var chatList = this.data.chatList

    //减少tapbar的badge
    var lessBadge = chatList[index].unreadCount
    websocket.lessBadge(lessBadge)

    //减少列表用户的badge
    chatList[index].unreadCount = 0
    this.setData({
      chatList: chatList
    })

    wx.navigateTo({
      url: '/pages/chat/chatForm/chatForm?id=' + chatId,
    })

  },
  openListen: function() {
    let that = this
    websocket.listenChatIndex().then(res => {
      console.log("chatIndex监听到消息:" + res)

      //ws监听到新消息,加入当前列表中
      let chatList = this.data.chatList
      for (var i in chatList) {
        //存在与目前list中
        if (chatList[i].lastChat.chatId == res.chatId) {
          var target = chatList[i]
          var newChatList = []


          target.unreadCount++;
          target.u1ToU2 = res.senderId < res.receiverId ? true : false
          target.lastChat.messageType = res.messageType
          target.lastChat.messageBody = res.messageBody
          target.lastChat.sendTime = res.sendTime

          chatList.splice(i, 1);
          console.log(chatList)

          newChatList.push(target)
          newChatList = newChatList.concat(chatList)

          that.setData({
            chatList: newChatList
          })
          that.openListen()
          return
        }
      }
      //不存在, 后端可以专门写个api
      that.onShow()
    })
  },
  onPullDownRefresh: function() {
    console.log("上拉刷新")
    this.setData({
      chatList: [],
      offsetTime: null,
      size: 10,
    })
    this.onShow()
    setTimeout(function callback() {
      wx.stopPullDownRefresh()
    }, 500)


  },
  onReachBottom: function() {
    console.log("拉到底")

    this.getChatList()
  },

})