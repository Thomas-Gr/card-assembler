const express = require('express')
const app = express()
const port = 3000
const fs = require('fs');

app.get('/save', (req, res) => {
  console.log("Saving id: " + req.query.id);
  fs.writeFile('positions/' + req.query.id, req.query.position, err => {}); 
})
app.use(express.static('.'))
app.listen(port, () => console.log(`Listening on port ${port}`))