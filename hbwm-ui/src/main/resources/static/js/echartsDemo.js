$(document).ready(function () {
    //柱状图1
    var myChart1 = echarts.init(document.getElementById('main1'));
    // 显示标题，图例和空的坐标轴
    myChart1.setOption({
        title : {
            text : '访问量数据统计'
        },
        tooltip : {},
        legend : {
            data : [ '访问量' ]
        },
        xAxis : [{
            data : [],
            axisLabel: {
                interval:0,
                rotate:40
            }
        }],
        yAxis : {},
        series : [ {
            name : '访问量',
            type : 'bar',
            data : [],
            itemStyle: {
                normal: {
                    color: '#009688'
                }
            }

        } ]
    });

    var names1 = []; //类别数组（实际用来盛放X轴坐标值）
    var nums1 = []; //销量数组（实际用来盛放Y坐标值）

    $.ajax({
        type : "get",
        async : true, //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url : "http://localhost/echarts1", //请求发送到ed处
        data : {},
        dataType : "json", //返回数据形式为json
        success : function(data) {
            //请求成功时执行该函数内容，result即为服务器返回的json对象
            if (data.chartOne) {
                for (var i = 0; i < data.chartOne.length; i++) {
                    names1.push(data.chartOne[i].date);//挨个取出类别并填入类别数组
                }
                for (var i = 0; i < data.chartOne.length; i++) {
                    nums1.push(data.chartOne[i].times); //挨个取出销量并填入销量数组
                }
                myChart1.hideLoading(); //隐藏加载动画
                myChart1.setOption({ //加载数据图表
                    xAxis : {
                        data : names1
                    },
                    series : [ {
                        // 根据名字对应到相应的系列
                        name : '访问量',
                        data : nums1
                    } ]
                });

            }

        },
        error : function(errorMsg) {
            //请求失败时执行该函数
            alert("图表请求数据失败!");
            myChart1.hideLoading();
        }
    });

    //柱状图2
    var myChart2 = echarts.init(document.getElementById('main2'));
    // 显示标题，图例和空的坐标轴
    myChart2.setOption({
        title : {
            text : '播放量数据统计'
        },
        tooltip : {},
        legend : {
            data : [ '播放量' ]
        },
        xAxis : [{
            data : [],
            axisLabel: {
                interval:0,
                rotate:40
            }
        }],
        yAxis : {},
        series : [ {
            name : '播放量',
            type : 'bar',
            data : [],
            itemStyle: {
                normal: {
                    color: '#009688'
                }
            }

        } ]
    });

    var names2 = []; //类别数组（实际用来盛放X轴坐标值）
    var nums2 = []; //销量数组（实际用来盛放Y坐标值）

    $.ajax({
        type : "get",
        async : true, //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url : "http://localhost/echarts2", //请求发送到echarts2处
        data : {},
        dataType : "json", //返回数据形式为json
        success : function(data) {
            //请求成功时执行该函数内容，result即为服务器返回的json对象
            if (data.chartTwo) {
                for (var i = 0; i < data.chartTwo.length; i++) {
                    names2.push(data.chartTwo[i].music_name);//挨个取出类别并填入类别数组
                }
                for (var i = 0; i < data.chartTwo.length; i++) {
                    nums2.push(data.chartTwo[i].play_volum); //挨个取出销量并填入销量数组
                }
                myChart2.hideLoading(); //隐藏加载动画
                myChart2.setOption({ //加载数据图表
                    xAxis : {
                        data : names2
                    },
                    series : [ {
                        // 根据名字对应到相应的系列
                        name : '播放量',
                        data : nums2
                    } ]
                });

            }

        },
        error : function(errorMsg) {
            //请求失败时执行该函数
            alert("图表请求数据失败!");
            myChart2.hideLoading();
        }
    });

    //柱状图3
    var myChart3 = echarts.init(document.getElementById('main3'));
    // 显示标题，图例和空的坐标轴
    myChart3.setOption({
        title : {
            text : '用户变化量数据统计'
        },
        tooltip : {},
        legend : {
            data : [ '用户变化量' ]
        },
        xAxis : [{
            data : [],
            axisLabel: {
                interval:0,
                rotate:40
            }
        }],
        yAxis : {},
        series : [ {
            name : '用户变化量',
            type : 'bar',
            data : [],
            itemStyle: {
                normal: {
                    color: '#009688'
                }
            }

        } ]
    });

    var names3 = []; //类别数组（实际用来盛放X轴坐标值）
    var nums3 = []; //销量数组（实际用来盛放Y坐标值）

    $.ajax({
        type : "get",
        async : true, //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url : "http://localhost/echarts3", //请求发送到ed处
        data : {},
        dataType : "json", //返回数据形式为json
        success : function(data) {
            //请求成功时执行该函数内容，result即为服务器返回的json对象
            if (data.chartThree) {
                for (var i = 0; i < data.chartThree.length; i++) {
                    names3.push(data.chartThree[i].date);//挨个取出类别并填入类别数组
                }
                for (var i = 0; i < data.chartThree.length; i++) {
                    nums3.push(data.chartThree[i].times); //挨个取出销量并填入销量数组
                }
                myChart3.hideLoading(); //隐藏加载动画
                myChart3.setOption({ //加载数据图表
                    xAxis : {
                        data : names3
                    },
                    series : [ {
                        // 根据名字对应到相应的系列
                        name : '用户变化量',
                        data : nums3
                    } ]
                });

            }

        },
        error : function(errorMsg) {
            //请求失败时执行该函数
            alert("图表请求数据失败!");
            myChart3.hideLoading();
        }
    })

    //柱状图4
    var myChart4 = echarts.init(document.getElementById('main4'));
    // 显示标题，图例和空的坐标轴
    myChart4.setOption({
        title : {
            text : '歌曲下载量数据统计'
        },
        tooltip : {},
        legend : {
            data : [ '歌曲下载量' ]
        },
        xAxis : [{
            data : [],
            axisLabel: {
                interval:0,
                rotate:40
            }
        }],
        yAxis : {},
        series : [ {
            name : '歌曲下载量',
            type : 'bar',
            data : [],
            itemStyle: {
                normal: {
                    color: '#009688'
                }
            }

        } ]
    });

    var names4 = []; //类别数组（实际用来盛放X轴坐标值）
    var nums4 = []; //销量数组（实际用来盛放Y坐标值）

    $.ajax({
        type : "get",
        async : true, //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url : "http://localhost/echarts4", //请求发送到ed处
        data : {},
        dataType : "json", //返回数据形式为json
        success : function(data) {
            //请求成功时执行该函数内容，result即为服务器返回的json对象
            if (data.chartFour) {
                for (var i = 0; i < data.chartFour.length; i++) {
                    names4.push(data.chartFour[i].music_name);//挨个取出类别并填入类别数组
                }
                for (var i = 0; i < data.chartFour.length; i++) {
                    nums4.push(data.chartFour[i].download_volum); //挨个取出销量并填入销量数组
                }
                myChart4.hideLoading(); //隐藏加载动画
                myChart4.setOption({ //加载数据图表
                    xAxis : {
                        data : names4
                    },
                    series : [ {
                        // 根据名字对应到相应的系列
                        name : '歌曲下载量',
                        data : nums4
                    } ]
                });

            }

        },
        error : function(errorMsg) {
            //请求失败时执行该函数
            alert("图表请求数据失败!");
            myChart4.hideLoading();
        }
    })
})


