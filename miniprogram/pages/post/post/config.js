var fileHost="https://zhb1nk.oss-cn-beijing.aliyuncs.com"
var config = {
  //aliyun OSS config
  uploadImageUrl: `${fileHost}`, //默认存在根目录，可根据需求改
  AccessKeySecret: 'BxZnR4AC2Ky1HLquWm0guQH9cWY5sg',
  OSSAccessKeyId: 'LTAI4Fyi7Xtpd3sZJasx9rtW',
  timeout: 87600 //这个是上传文件时Policy的失效时间
};
module.exports = config
