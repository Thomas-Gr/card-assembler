const fs = require('fs');
const sizeOf = require('image-size');

const picturesFolder = 'cards';

var images = [];
fs.readdir(picturesFolder, (err, files) => {
  files.forEach(file => {
  	if (file.includes('png') || file.includes('jpg')|| file.includes('jpeg')) {
  		if (!(file.includes('Bandai') || file.includes('-Green') || file.includes('-Red'))) {
		  	var filePath = picturesFolder + '/' + file;
			var dimensions = sizeOf(filePath);

			images.push({path: filePath, width: dimensions.width, height: dimensions.height});
		}
	}
  });

  fs.writeFile("images_list.json", JSON.stringify(images),  err => {}); 
});