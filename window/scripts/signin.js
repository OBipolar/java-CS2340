tileList = [];
tileRow = 3;
tileCol = 5;

$(document).ready(function(){

    var contentContainer = $('#content');

    for(var i = 0; i < tileRow; i++)
        tileList[i] = [];

    newPic = new Picture({tagName:'img', url:'./storage/pictures/pic8.jpg'});
    newSlide = new Slide(newPic, null);
    newTile = new Tile([newSlide]);
    tileList[0][0] = new TileView({container: contentContainer,
        id: "tile0",
        tile: newTile});

    newPic = new Picture({tagName:'img', url:'./storage/pictures/pic1.jpg'});
    newSlide = new Slide(newPic, null);
    newTile = new Tile([newSlide]);
    tileList[0][1] = new TileView({container: contentContainer,
        id: "tile1",
        tile: newTile});

    newPic = new Picture({tagName:'img', url:'./storage/pictures/pic6.jpg'});
    newSlide = new Slide(newPic, null);
    newTile = new Tile([newSlide]);
    tileList[0][2] = new TileView({container: contentContainer,
        id: "tile2",
        tile: newTile});

    newPic = new Picture({tagName:'img', url:'./storage/pictures/pic13.jpg'});
    newSlide = new Slide(newPic, null);
    newTile = new Tile([newSlide]);
    tileList[0][3] = new TileView({container: contentContainer,
        id: "tile3",
        tile: newTile});

    newPic = new Picture({tagName:'img', url:'./storage/pictures/pic16.jpg'});
    newSlide = new Slide(newPic, null);
    newTile = new Tile([newSlide]);
    tileList[0][4] = new TileView({container: contentContainer,
        id: "tile4",
        tile: newTile});

    newPic = new Picture({tagName:'img', url:'./storage/pictures/pic15.jpg'});
    newSlide = new Slide(newPic, null);
    newTile = new Tile([newSlide]);
    tileList[1][0] = new TileView({container: contentContainer,
        id: "tile5",
        tile: newTile});

    newPic = new Picture({tagName:'img', url:'./window/images/signup.jpg'});
    newSlide = new Slide(newPic, null);
    newTile = new Tile([newSlide]);
    tileList[1][1] = new TileView({container: contentContainer,
        id: "tile6",
        tile: newTile});
    


    newTextField = new InputField({tagName: 'div', text: '用户名'})
    newTextField2 = new InputField({tagName: 'div', text: '密码'})


    newInputForm = new InputForm({tagName: 'div', formList: [newTextField, newTextField2]});
    newSlide = new Slide(newInputForm, null);
    newTile = new Tile([newSlide]);
    tileList[1][2] = new TileView({container: contentContainer,
        id: "tile7",
        tile: newTile});

    newPic = new Picture({tagName:'img', url:'./window/images/signin.jpg'});
    newSlide = new Slide(newPic, null);
    newTile = new Tile([newSlide]);
    tileList[1][3] = new TileView({container: contentContainer,
        id: "tile8",
        tile: newTile});

    newPic = new Picture({tagName:'img', url:'./storage/pictures/pic12.jpg'});
    newSlide = new Slide(newPic, null);
    newTile = new Tile([newSlide]);
    tileList[1][4] = new TileView({container: contentContainer,
        id: "tile9",
        tile: newTile});

    newPic = new Picture({tagName:'img', url:'./storage/pictures/pic11.jpg'});
    newSlide = new Slide(newPic, null);
    newTile = new Tile([newSlide]);
    tileList[2][0] = new TileView({container: contentContainer,
        id: "tile10",
        tile: newTile});

    newPic = new Picture({tagName:'img', url:'./storage/pictures/pic9.jpg'});
    newSlide = new Slide(newPic, null);
    newTile = new Tile([newSlide]);
    tileList[2][1] = new TileView({container: contentContainer,
        id: "tile11",
        tile: newTile});

    newPic = new Picture({tagName:'img', url:'./storage/pictures/pic4.jpg'});
    newSlide = new Slide(newPic, null);
    newTile = new Tile([newSlide]);
    tileList[2][2] = new TileView({container: contentContainer,
        id: "tile12",
        tile: newTile});

    newPic = new Picture({tagName:'img', url:'./storage/pictures/pic14.jpg'});
    newSlide = new Slide(newPic, null);
    newTile = new Tile([newSlide]);
    tileList[2][3] = new TileView({container: contentContainer,
        id: "tile13",
        tile: newTile});

    newPic = new Picture({tagName:'img', url:'./storage/pictures/pic10.jpg'});
    newSlide = new Slide(newPic, null);
    newTile = new Tile([newSlide]);
    tileList[2][4] = new TileView({container: contentContainer,
        id: "tile14",
        tile: newTile});

    
})