export default{
  decode:function(base64str){
    let result = Buffer.from(base64str,'base64').toString('utf-8');
    return result;
  },
  encode:function(str){
    let result = Buffer.from(str,'utf-8').toString('base64');
    return result;
  }
}
