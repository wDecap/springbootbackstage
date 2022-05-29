<template>
    <div>
        <el-row>
            <el-col :span="12">
                <div id="main" style="width: 500px; height: 500px"></div>
            </el-col>

            <el-col :span="12">
               <div id="cat" style="width: 500px; height: 500px"></div>
            </el-col>
            </el-row>
    </div>
</template>

<script>
    import * as echarts from  'echarts'
    export default {
        name: "Home",
        data(){
            return{

            }
        },
        mounted() { //页面元素渲染之后再触发
            var chartDom = document.getElementById('main');
            var myChart = echarts.init(chartDom);
            var Option;

            setTimeout(function () {
                Option = {
                    legend: {},
                    tooltip: {
                        trigger: 'axis',
                        showContent: false
                    },
                    dataset: {
                        source: [
                            ['product', '2018', '2019', '2020', '2021', '2022', '2023'],
                            ['Milk Tea', 56.5, 82.1, 88.7, 70.1, 53.4, 85.1],
                            ['Matcha Latte', 51.1, 51.4, 55.1, 53.3, 73.8, 68.7],
                            ['Cheese Cocoa', 40.1, 62.2, 69.5, 36.4, 45.2, 32.5],
                            ['Walnut Brownie', 25.2, 37.1, 41.2, 18, 33.9, 49.1]
                        ]
                    },
                    xAxis: { type: 'category' },
                    yAxis: { gridIndex: 0 },
                    grid: { top: '55%' },
                    series: [
                        {
                            type: 'line',
                            smooth: true,
                            seriesLayoutBy: 'row',
                            emphasis: { focus: 'series' }
                        },
                        {
                            type: 'line',
                            smooth: true,
                            seriesLayoutBy: 'row',
                            emphasis: { focus: 'series' }
                        },
                        {
                            type: 'line',
                            smooth: true,
                            seriesLayoutBy: 'row',
                            emphasis: { focus: 'series' }
                        },
                        {
                            type: 'line',
                            smooth: true,
                            seriesLayoutBy: 'row',
                            emphasis: { focus: 'series' }
                        },
                        {
                            type: 'pie',
                            id: 'pie',
                            radius: '30%',
                            center: ['50%', '25%'],
                            emphasis: {
                                focus: 'self'
                            },
                            label: {
                                formatter: '{b}: {@2012} ({d}%)'
                            },
                            encode: {
                                itemName: 'product',
                                value: '2012',
                                tooltip: '2012'
                            }
                        }
                    ]
                };
                myChart.on('updateAxisPointer', function (event) {
                    const xAxisInfo = event.axesInfo[0];
                    if (xAxisInfo) {
                        const dimension = xAxisInfo.value + 1;
                        myChart.setOption({
                            series: {
                                id: 'pie',
                                label: {
                                    formatter: '{b}: {@[' + dimension + ']} ({d}%)'
                                },
                                encode: {
                                    value: dimension,
                                    tooltip: dimension
                                }
                            }
                        });
                    }
                });
                myChart.setOption(Option);

            });
            catOption = {
                title: {
                    text: '',
                    subtext: '',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'cross',
                        label: {
                            backgroundColor: '#6a7985'
                        }
                    }
                },
                legend: {
                    data: ['Email', 'Union Ads', 'Video Ads', 'Direct', 'Search Engine']
                },
                toolbox: {
                    feature: {
                        saveAsImage: {}
                    }
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: [
                    {
                        type: 'category',
                        boundaryGap: false,
                        data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    }
                ],
                series: [
                    {
                        name: 'Email',
                        type: 'line',
                        stack: 'Total',
                        areaStyle: {},
                        emphasis: {
                            focus: 'series'
                        },
                        data: [120, 132, 101, 134, 90, 230, 210]
                    },
                    {
                        name: 'Union Ads',
                        type: 'line',
                        stack: 'Total',
                        areaStyle: {},
                        emphasis: {
                            focus: 'series'
                        },
                        data: [220, 182, 191, 234, 290, 330, 310]
                    },
                    {
                        name: 'Video Ads',
                        type: 'line',
                        stack: 'Total',
                        areaStyle: {},
                        emphasis: {
                            focus: 'series'
                        },
                        data: [150, 232, 201, 154, 190, 330, 410]
                    },
                    {
                        name: 'Direct',
                        type: 'line',
                        stack: 'Total',
                        areaStyle: {},
                        emphasis: {
                            focus: 'series'
                        },
                        data: [320, 332, 301, 334, 390, 330, 320]
                    },
                    {
                        name: 'Search Engine',
                        type: 'line',
                        stack: 'Total',
                        label: {
                            show: true,
                            position: 'top'
                        },
                        areaStyle: {},
                        emphasis: {
                            focus: 'series'
                        },
                        data: [820, 932, 901, 934, 1290, 1330, 1320]
                    }
                ]
            };
            var catDom = document.getElementById('cat');
            var catChart = echarts.init(catDom);
            var catOption;
            // this.request.get("/echarts/example").then(res =>{
            // //     catOption.xAxis.data =res.data.x
            //     catOption.series[0].data=res.data.y1
            //     catOption.series[1].data=res.data.y2
            //     catOption.series[2].data=res.data.y3
            //     catOption.series[3].data=res.data.y4
            //     catOption.series[4].data=res.data.y5
                //数据准备完毕之后再set
                catChart.setOption(catOption)
            // })
            // this.request.get("/echarts/members").then(res =>{
            //     catOption.series[0].data=res.data
            //     catOption.series[1].data=res.data
            //     catChart.setOption(catOption)
            // })


        }
    }
</script>

<style scoped>

</style>