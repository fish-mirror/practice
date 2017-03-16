<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- 实习统计 -->

<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <ul class="nav nav-pills">
        <li class="active"><a href="#">最新</a></li>

    </ul>
    <div number="main" style="width: 600px;height:400px;"></div>

</div>

<script src="js/echarts.min.js"></script>
<script>

    // 指定图表的配置项和数据
    function randomData() {
        return Math.round(Math.random() * 100);
    }
    $.get('json/china.json', function (chinaJson) {
        echarts.registerMap('china', chinaJson);

        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
        option = {
            title: {
                text: '格林芬多学院实习地理分布',
                subtext: '更新日期：2015-03-31',
                left: 'center'
            },
            tooltip: {
                trigger: 'item'
            },
            legend: {
                orient: 'vertical',
                left: 'left',
            },
            visualMap: {
                min: 0,
                max: 200,
                left: 'left',
                top: 'bottom',
                text: ['高', '低'],           // 文本，默认为数值文本
                calculable: true
            },
            toolbox: {
                show: true,
                orient: 'vertical',
                left: 'right',
                top: 'center',
                feature: {
                    dataView: {readOnly: false},
                    restore: {},
                    saveAsImage: {}
                }
            },
            series: [
                {
                    name: '实习人数',
                    type: 'map',
                    mapType: 'china',
                    roam: false,
                    label: {
                        normal: {
                            show: true
                        },
                        emphasis: {
                            show: true
                        }
                    },
                    data: [
                        {name: '北京', value: randomData()},
                        {name: '天津', value: randomData()},
                        {name: '上海', value: randomData()},
                        {name: '重庆', value: randomData()},
                        {name: '河北', value: randomData()},
                        {name: '河南', value: randomData()},
                        {name: '云南', value: randomData()},
                        {name: '辽宁', value: randomData()},
                        {name: '黑龙江', value: randomData()},
                        {name: '湖南', value: randomData()},
                        {name: '安徽', value: randomData()},
                        {name: '山东', value: randomData()},
                        {name: '新疆', value: 2},
                        {name: '江苏', value: randomData()},
                        {name: '浙江', value: 150},
                        {name: '江西', value: randomData()},
                        {name: '湖北', value: randomData()},
                        {name: '广西', value: randomData()},
                        {name: '甘肃', value: randomData()},
                        {name: '山西', value: randomData()},
                        {name: '内蒙古', value: 20},
                        {name: '陕西', value: randomData()},
                        {name: '吉林', value: randomData()},
                        {name: '福建', value: randomData()},
                        {name: '贵州', value: randomData()},
                        {name: '广东', value: randomData()},
                        {name: '青海', value: 10},
                        {name: '西藏', value: 10},
                        {name: '四川', value: randomData()},
                        {name: '宁夏', value: randomData()},
                        {name: '海南', value: randomData()},
                        {name: '台湾', value: randomData()},
                        {name: '香港', value: randomData()},
                        {name: '澳门', value: randomData()}
                    ]
                }
            ]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);

    });

</script>
</body>
</html>