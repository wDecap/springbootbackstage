<template>
  <div>
    <div id="container" style="width: 100%; height: calc(100vh - 100px)"></div>

    <div id="info"></div>
  </div>
</template>

<script>
var content = [
  "<div style='font-size: 14px; color: red; width: 200px; height: 50px'><span>北区七栋宿舍楼<p>[113.0943013 , 23.4150228]</p></span></div>"
];


export default {
  name: "Map",
  data() {
    return {}
  },
  created() {

  },
  mounted() {
    // 创建地图实例
    var map = new AMap.Map("container", {
      zoom: 15.5,
      center: [113.09430138168001,23.415022898223175],
      resizeEnable: true
    })

    var infoWindow = new AMap.InfoWindow({
      anchor: 'top-right',
      content: content.join("<br>")  //传入 dom 对象，或者 html 字符串
    });

    var clickHandler = function(e) {
      console.log('您在[ '+e.lnglat.getLng()+','+e.lnglat.getLat()+' ]的位置点击了地图！');

      infoWindow.open(map, [113.09430138168001,23.415022898223175]);
    };

    // 绑定事件
    // map.on('click', clickHandler);

    var marker = new AMap.Marker({
      position: new AMap.LngLat(113.09430138168001,23.415022898223175),   // 经纬度对象，也可以是经纬度构成的一维数组[113.09430138168001,23.415022898223175]
      title: '北区七栋',
      icon: '//vdata.amap.com/icons/b18/1/2.png',
      // content: '<div style="font-size: 12px; width: 100px">摆烂宿舍426</div>'
    })
    marker.on('click', clickHandler);


    // 将创建的点标记添加到已有的地图实例：
    map.add(marker);


    // path 是驾车导航的起、途径和终点，最多支持16个途经点
    var path = []
    path.push([113.09271411182145, 23.40933218010135])
    path.push([113.0907885038935, 23.41048905005677])
    path.push([113.09430138168001,23.415022898223175])

    map.plugin('AMap.DragRoute', function () {
      var route = new AMap.DragRoute(map, path, AMap.DrivingPolicy.LEAST_FEE)
      // 查询导航路径并开启拖拽导航
      route.search()
    })

    var polyLine = new AMap.Polyline({
      path: path,
      strokeWeight: 5,
      borderWeight: 5, // 线条宽度，默认为 1
      strokeStyle: "solid",
      strokeColor: '#DC143C', // 线条颜色
      lineJoin: 'round' // 折线拐点连接处样式
    })
    map.add(polyLine)


    //实例化城市查询类
    map.plugin('AMap.Geolocation', function() {
      var geolocation = new AMap.Geolocation({
        // 是否使用高精度定位，默认：true
        enableHighAccuracy: true,
        // 设置定位超时时间，默认：无穷大
        timeout: 10000,
        // 定位按钮的停靠位置的偏移量
        offset: [10, 20],
        //  定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
        zoomToAccuracy: true,
        //  定位按钮的排放位置,  RB表示右下
        position: 'RB'
      })

      geolocation.getCurrentPosition(function(status,result){
        if(status=='complete'){
          onComplete(result)
        }else{
          onError(result)
        }
      });

      function onComplete (data) {
        // data是具体的定位信息
        console.log(data)
      }

      function onError (data) {
        // 定位出错
        console.error(data)
      }
    })

  },
  methods: {}
}
</script>

<style>

</style>
