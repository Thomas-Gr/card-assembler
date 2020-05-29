<?php
	include('box.php');

	$isIndex = true;
	if ($_GET['type'] == 'pokemon' && is_numeric($_GET['id'])) {
		include('data/pokemon/' . $_GET['id'] . '.php');
		$isIndex = false;
	} else if ($_GET['type'] == 'artist' && is_numeric($_GET['id'])) {
		include('data/artists/' . $_GET['id'] . '.php');
		$isIndex = false;
	} else if ($_GET['type'] == 'expansion' && is_numeric($_GET['id'])) {
		include('data/expansions/' . $_GET['id'] . '.php');
		$isIndex = false;
	}

	if ($isIndex) {
		$cards = array(
			array("link" => "AbraVendingS1.jpg"),
			array("link" => "AerodactylVendingS2.jpg"),
			array("link" => "AlakazamCommunicationEvolutionPromo.jpg"),
			array("link" => "ArbokVendingS3.jpg"),
			array("link" => "ArticunoPhoneCard.jpg"),
			array("link" => "ArcanineSquirtleDeck32.jpg")
		);
	}
?>

<!DOCTYPE HTML>
<html>
	<head>
		<title>PokeCards Mosaic</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="assets/css/main.css" />
	</head>
	<body id="top">
		<section id="banner" data-video="images/banner">
			<div class="inner">
				<header>
					<h1>PokeCards Mosaic</h1>
					<p>Pokémon cards made out of Pokémon cards.</p>
				</header>
				<a href="#main" class="more">Learn More</a>
			</div>
		</section>

		<div id="main">
			<div class="inner">
				<?php 

					if ($isIndex) {
						echo "<h1>Highlights</h1>";
					}

				 ?>
				<div class="thumbnails">
					<?php
						foreach ($cards as $card) {
						    showCard($card);
						}
					?>
				</div>

				<h1>Discover more</h1>

				<div id="discover">
					<div class="search">
						<h2>Pokemon</h2>

						<form action="index.php" method="get">
							<input type="hidden" name="type" value="pokemon">
						    <select name="id">
				               <?php include('data/lists/pokemon.php') ?>
				            </select>

				            <input type="submit" value="Search">
			        	</form>
					</div>

					<div class="search">
						<h2>Artist</h2>

						<form action="index.php" method="get">
							<input type="hidden" name="type" value="artist">
						    <select name="id">
				               <?php include('data/lists/artists.php') ?>
				             </select>

				            <input type="submit" value="Search">
			        	</form>
					</div>

					<div class="search">
						<h2>Expansion</h2>

						<form action="index.php" method="get">
							<input type="hidden" name="type" value="expansion">
						    <select name="id">
				               <?php include('data/lists/expansions.php') ?>
				             </select>

				            <input type="submit" value="Search">
			        	</form>
					</div>
				</div>
			</div>
		</div>

		<footer id="footer">
			<div class="inner">
				<h2>Talk about us on social media</h2>

				<ul class="icons">
					<li><a href="https://www.facebook.com/sharer/sharer.php?u=https://www.test.com" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
					<li><a href="https://twitter.com/home?status=https://www.test.com TEST" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
					<li><a href="https://pinterest.com/pin/create/button/?url=https://www.test.com&media=&description=TEST" class="icon fa-pinterest"><span class="label">Pinterest</span></a></li>
				</ul>

				<p class="copyright">© 1995–2020 Nintendo/Creatures Inc./GAME FREAK inc. Pokémon, Pokémon character names are trademarks of Nintendo. All rights to their respective copyright holders. Pokemon Cards mosaics are copyright of the owners of this site.</p>
				<p class="copyright">Author: <a href="https://www.grilletta.fr">Arca`</a> | Core design: <a href="https://templated.co">Templated</a> | Card scans: <a href="http://bulbapedia.bulbagarden.net/">Bulbapedia</a>, <a href="https://collectorviper.livejournal.com/">Viper Fox</a></p>
			</div>
		</footer>

		<script src="assets/js/jquery.min.js"></script>
		<script src="assets/js/jquery.scrolly.min.js"></script>
		<script src="assets/js/jquery.poptrox.min.js"></script>
		<script src="assets/js/skel.min.js"></script>
		<script src="assets/js/util.js"></script>
		<script src="assets/js/main.js"></script>
	</body>
</html>