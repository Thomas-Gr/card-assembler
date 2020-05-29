var clipper = require('image-clipper');
var canvas = require('canvas');
const fs = require('fs');

clipper.configure('canvas', canvas);

var cardList = JSON.parse(fs.readFileSync('images_list.json', 'utf8'));
var total = 0;

cardList.forEach(function (card, i) {
	if (card.process && fs.existsSync('positions/' + i, 'utf8')) {
		total++;

		var pos = fs.readFileSync('positions/' + i, 'utf8').split(",");

		clipper(card.path, function() {
		    this.crop(parseInt(pos[0]), parseInt(pos[1]), parseInt(pos[2]), parseInt(pos[3]))
		    .quality(100)
		    .toFile(card.path.replace("cards/", "cards_after/"), function() {
		       console.log(card.path);
		   });
		});
	}	
});

console.log(total + "/" + cardList.length);