google.setOnLoadCallback(function() {

  $('div.elo_history').each(function() {
    var data = google.elemToData(this);
    var chart = new google.visualization.ComboChart(this);
    chart.draw(data, {
      series: [ { color: "blue", type: "area", lineWidth: 2 } ],
      width: 460,
      height: 340,
      axisTitlePosition: 'none',
      chartArea:{left:"10%",top:"3%",width:"90%",height:"80%"},
      title: $(this).attr('title'),
      titlePosition: 'in',
      hAxis: {textStyle: {fontSize: 0.1}}
    });
  });

  $('div.win_stats').each(function() {
    var data = google.elemToData(this);
    var chart = new google.visualization.PieChart(this);
    chart.draw(data, {
      width: 312,
      height: 200,
      chartArea:{left:"0%",width:"100%",height:"100%"},
      is3D: true,
    });
  });

  $('div.elo_distribution').each(function() {
    var data = google.elemToData(this);
    var chart = new google.visualization.ScatterChart(this);
    chart.draw(data, {
      width: 747,
      height: 500,
      //axisTitlePosition: 'none',
      chartArea:{left:"5%",top:"3%",width:"78%",height:"92%"},
      title: $(this).attr('title'),
      titlePosition: 'in',
      pointSize: 3,
    });
  });

  $('div.end_distribution').each(function() {
    var data = google.elemToData(this);
    var chart = new google.visualization.PieChart(this);
    chart.draw(data, {
      width: 747,
      height: 500,
      chartArea:{left:"0%",top:"5%",width:"100%",height:"95%"},
      is3D: true,
      title: $(this).attr('title'),
    });
  });
});
