const fs = require('fs');
const sizeOf = require('image-size');

const picturesFolder = 'cards';

var images = [];
const seriesFolder = '/Users/grillett/dev/Pokellection/resources/series';
const positionsFolder = '/Users/grillett/dev/card-assembler/cards-formatter/positions';

var pokemonCards = ["FIRE", "WATER", "LIGHTNING", "PSYCHIC", "FIGHTING", "GRASS", "COLORLESS", "DARKNESS", "METAL"];
var wizards = ["1996.json", "1997.json", "1998.json", "1999.json", "2000.json", "2001-2005.json", "ANA-JR.json", "Awakening Legends.json", "Bulbasaur Deck.json", "Challenge from the Darkness.json", "Championship.json", "Chikorita Half Deck.json", "CoroCoro Best Photo Contest.json", "Crossing the Ruins.json", "Darkness, and to Light.json", "Evolution Communication Campaign.json", "Expansion Pack.json", "Game Boy.json", "Gold, Silver, to a New World.json", "Green Deck.json", "Guren Town Gym.json", "Gym.json", "Hanada City Gym.json", "How I Became a Pokemon Card.json", "Jumbo.json", "Kuchiba City Gym.json", "Leaders' Stadium.json", "Lucky Stadium.json", "Mystery of the Fossils.json", "Nivi City Gym.json", "Pikachu Illustrator.json", "Pokemon Fan Club.json", "Pokemon Jungle.json", "Pokemon Song Best Collection.json", "Premium File 1.json", "Premium File 2.json", "Premium File 3.json", "Rainbow Island.json", "Red Deck.json", "Rocket Gang.json", "Series 1 (Blue).json", "Series 2 (Red).json", "Series 3 (Green).json", "Squirtle Deck.json", "Tamamushi City Gym.json", "Totodile Half Deck.json", "Trade Please.json", "Tropical Island.json", "Tropical Mega Battle Phone Cards.json", "Vending Machine.json", "Yamabuki City Gym.json"];

var images = [];
var data = {};
fs.readdir(seriesFolder, (err, files) => {
  files.forEach(file => {
  	let serie = JSON.parse(fs.readFileSync(seriesFolder + "/" + file));

  	for (card in serie.cards) {
  		var v = serie.cards[card];
		data[v.picture] = ({id: v.id, process: wizards.includes(file) && pokemonCards.includes(v.type)});
  	}
  });

  fs.readdir(picturesFolder, (err, files) => {
	  files.forEach(file => {
	  	if (file.includes('png') || file.includes('jpg')|| file.includes('jpeg')) {
	  		if (!(file.includes('Bandai') || file.includes('-Green') || file.includes('-Red'))) {
			  	var filePath = picturesFolder + '/' + file;
				var dimensions = sizeOf(filePath);

				images.push({path: filePath, width: dimensions.width, height: dimensions.height, process: data[file] != null && data[file].process});
			}
		}
	  });

	  fs.writeFile("images_list.json", JSON.stringify(images),  err => {}); 
	});
});

var positions = [];
fs.readdir(positionsFolder, (err, files) => {
  files.forEach(file => {
		positions.push(file);
  });

  fs.writeFile("positions_list.json", JSON.stringify(positions),  err => {}); 
});






