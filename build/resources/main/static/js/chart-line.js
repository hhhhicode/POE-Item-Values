/*
body를 파싱하기 전에 scipt를 먼저 읽으므로 오류가 발생하므로,
웹 브라우저의 모든 구성요소에 대한 로드가 끝났을 때 호출되도록 하는 함수 window.onload 사용
*/
let smooth = true;

function chartRun(currencyId, chartData) {
    window.onload = function () {
        let ctx = document.getElementById(currencyId);

        let chart = drawLineChart(ctx, chartData);
        for (let i = 0; i < actions.length; i++) {
            actions[i].handler(chart);
        }
    };
}

function drawLineChart(ctx, chartData) {
    return new Chart(ctx, {
        type: 'line',
        data: {
            labels: ["1", "2", "3", "4", "5", "6", "7"],
            datasets: [{
                label: "Dataset #1",
                backgroundColor: "rgba(255,99,132,0.2)",
                borderColor: "rgba(255,99,132,1)",
                borderWidth: 2,
                hoverBackgroundColor: "rgba(255,99,132,0.4)",
                hoverBorderColor: "rgba(255,99,132,1)",
                data: chartData,
            }]
        },
        options: {
            plugins: {
                filler: {
                    propagate: false,
                },
                legend: {
                    display: false
                },
            },
            elements: {
                point: {
                    radius: 0,
                }
            },
            interaction: {
                intersect: false,
            },
            scales: {
                x: {
                    grid: {
                        display: false,
                    },
                    display: false,
                },
                y: {
                    grid: {
                        display: false,
                    },
                    display: false,
                }
            }
        },
    });
}

const actions = [
    {
        name: 'Fill: start',
        handler: (chart) => {
            chart.data.datasets.forEach(dataset => {
                dataset.fill = 'start';
            });
            chart.update();
        }
    },
    {
        name: 'Smooth',
        handler(chart) {
            chart.options.elements.line.tension = smooth ? 0.4 : 0;
            chart.update();
        }
    }
];