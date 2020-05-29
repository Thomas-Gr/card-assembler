<?php
	if (is_numeric($_GET['id'])) {
		include('data/pictures/' . $_GET['id'] . '.php');

		$path = $card . "/" . explode(".", $card, 2)[0];
	}
?>

<!DOCTYPE HTML>
<html>
	<head>
		<title>Pokecards mosaic</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="assets/css/main.css" />
		<script src="openseadragon/openseadragon.min.js"></script>
	</head>
	<body>
		<div id="mosaic" style="width: 1200px; height: 600px;"></div>

		<script type="text/javascript">
		    var viewer = OpenSeadragon({
		        id: "mosaic",
		        prefixUrl: "openseadragon/images/",
		        tileSources: "images/split/<?php echo $path ?>.dzi",
		        minZoomImageRatio: 0.2,
		        maxZoomPixelRatio: 3
		    });
		</script>
	</body>
</html>