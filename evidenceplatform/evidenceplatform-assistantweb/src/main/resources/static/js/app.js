var indexdemo = {
  template:'\
  <div id="index_container" style="z-index:1; background:#999; position:absolute; left:0px; top:0px; width:100%; height:100%;">                                                  \
        <div style="width:100%; height:20; margin:0 auto; text-align:center; margin-top:120px; margin-bottom:100px">                                                           \
            <h1 align="center" style="color:#000; font-size:50px">腾讯云视频</h1>                                                                                              \
        </div>                                                                                                                                                                 \
        <div class="div_item_container" style="margin: 0 auto; width:800px;">    <!--1060-->                                                                                             \
            <!--<div align="center" style="width:260px; height:300px; display: inline-block;">                                                                                     \
                <img src="../assets/pushplay.png" onClick="window.location.href(\'http://img.qcloud.com/open/qcloud/video/act/avtivex_demo/demo/testdemo/testdemo.html\')"/>    \
                <br/><br/><br/>                                                                                                                                                \
                <h2 align="center" style="color:#000;">测试DEMO</h2>                                                                                                           \
            </div>-->                                                                                                                                                             \
            <div align="center" style="width:260px; height:300px; display: inline-block;">                                                                                     \
                <img src="../assets/edu.png" onClick="window.location.href(\'http://img.qcloud.com/open/qcloud/video/act/avtivex_demo/demo/liveroomdemo/liveroom.html\')" />    \
                <br/><br/><br/>                                                                                                                                                \
                <h2 align="center" style="color:#000;">直播体验室</h2>                                                                                                         \
            </div>                                                                                                                                                             \
            <div align="center" style="width:260px; height:300px; display: inline-block;">                                                                                     \
                <img src="../assets/cs.png" onClick="window.location.href(\'http://img.qcloud.com/open/qcloud/video/act/avtivex_demo/demo/doubleroomdemo/doubleroom.html\')" /> \
                <br /><br/><br/>                                                                                                                                               \
                <h2 style="color:#000;">双人视频</h2>                                                                                                                          \
            </div>                                                                                                                                                             \
            <div align="center" style="width:260px; height:300px; display: inline-block;">                                                                                     \
                <img src="../assets/court.png" onClick=\'window.location.href("http://img.qcloud.com/open/qcloud/video/act/avtivex_demo/demo/multroomdemo/multroom.html")\' />  \
                <br/><br/><br/>                                                                                                                                                \
                <h2 style="color:#000;">多人视频</h2>                                                                                                                          \
            </div>                                                                                                                                                             \
        </div>\
    </div>'
};

var router = new VueRouter({
  routes:[
      {
          path:'/',
          name: 'indexdemo',
          component:indexdemo
      },
  ]
});

var app = new Vue({
  el: '#vue-app',
  router: router,
})
