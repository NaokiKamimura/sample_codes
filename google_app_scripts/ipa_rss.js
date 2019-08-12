function getIpaRSS() {
  var feedURL = "https://www.ipa.go.jp/security/rss/alert.rdf";
  var response = UrlFetchApp.fetch(feedURL);
  var xml = XmlService.parse(response.getContentText());
  var rssNameSpace = XmlService.getNamespace('http://purl.org/rss/1.0/');
  var dcNameSpace = XmlService.getNamespace('dc', 'http://purl.org/dc/elements/1.1/');
  var items = xml.getRootElement().getChildren('item', rssNameSpace);
  var msg = [];
  // タイトルを先頭に追加
  msg.push(xml.getRootElement().getChild('channel', rssNameSpace).getChildText('title', rssNameSpace));
  var today = new Date();
  for(var i = 0; i < items.length; i++) {
    var year = items[i].getChildText('date', dcNameSpace).slice(0, 4);
    var month = items[i].getChildText('date', dcNameSpace).slice(5, 7);
    var day = items[i].getChildText('date', dcNameSpace).slice(8, 10);
    var itemDate = new Date(year, month, day);
    // 今日以降の記事が配信されていれば
    // FIXME: 多分うまく比較できていない
    if (itemDate >= today){
      msg.push(items[i].getChildText('title', rssNameSpace) + String.fromCharCode(10) + items[i].getChildText('link', rssNameSpace));
    }
  }
  Logger.log(msg.join(String.fromCharCode(10)));
}
