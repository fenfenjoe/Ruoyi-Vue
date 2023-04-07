import axios from 'axios'//引入axios
import base64Util from '../base64.js'

let gitee_access_token='4583bbcfca12e2c3db1bc46850b4f424';

export default{
  getFileContent:async function(owner,repo,path) {
    path = encodeURI(path);
    //'%2Fweb3%2Fweb3.md'
    let url = 'https://gitee.com/api/v5/repos/'+owner+'/'+repo+'/contents/'+path;
    return axios({
      method:'get',
      url:url,
      data:{
        access_token:gitee_access_token,
        ref:'master'
      }
    }).then((res)=>{
      res.data.content = base64Util.decode(res.data.content);
      return res;
    });
  },
  getTree:async function(owner,repo,sha){
    let url = 'https://gitee.com/api/v5/repos/'+owner+'/'+repo+'/git/trees/'+sha;

    return axios({
      method:'get',
      url:url,
      data:{
        access_token:gitee_access_token
      }
    });
  }
}
