/*
**  Project STP
**  index.js
**  script file for the index page
*/


// global variables
tileList = [];
tileRow  = 3;
tileCol  = 5;
tileWidth = 0;
tileHeight = 0;
contentContainer = null;
pictureList = [];

$(document).ready(function(){

    contentContainer = $('#content');

    for(var i = 0; i < 16; i++)
        pictureList.push("./storage/pictures/pic" + (i+1) + ".jpg");

    // initialize all tiles for this page
    for(var i = 0; i < tileRow; i++) {
        tileList[i] = [];
        for(var j = 0; j < tileCol; j++) {
            var num = i*tileCol+j
            var newPic = new Picture({tagName: 'img', url:pictureList[num]});
            var newBack = new SlideElement({content: 'back'+num});
            var newBack2 = new SlideElement({content: 'back'+num});
            var newBack3 = new SlideElement({content: 'back'+num});
            var newSlide = new Slide(newPic, newBack);
            var newSlide2 = new Slide(newBack2, newBack3);
            var newTile = new Tile();
            newTile.addSlide(newSlide);
            newTile.addSlide(newSlide2);
            tileList[i][j] = new TileView({container: contentContainer,
                id: "tile"+num,
                tile: newTile});
        }
    }




    $('.flip-wrapper').quickFlip();
    $('.flip-wrapper').click(function(){
        $(this).quickFlipper();
    })
})