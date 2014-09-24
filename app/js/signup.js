tileList = [];
tileRow = 3;
tileCol = 5;

$(document).ready(function(){

    var contentContainer = $('#content');

    for(var i = 0; i < tileRow; i++)
        tileList[i] = [];

    newTextField = new InputField({tagName: 'div', text: '用户名'})
    newTextField2 = new InputField({tagName: 'div', text: '邮箱地址'})


    newInputForm = new InputForm({tagName: 'div', formList: [newTextField, newTextField2]});
    newSlide = new Slide(newInputForm, null);
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

    newPic = new Picture({tagName:'img', url:'./storage/pictures/pic2.jpg'});
    newSlide = new Slide(newPic, null);
    newTile = new Tile([newSlide]);
    tileList[0][2] = new TileView({container: contentContainer,
        id: "tile2",
        tile: newTile});

    newPic = new Picture({tagName:'img', url:'./storage/pictures/pic3.jpg'});
    newSlide = new Slide(newPic, null);
    newTile = new Tile([newSlide]);
    tileList[0][3] = new TileView({container: contentContainer,
        id: "tile3",
        tile: newTile});

    newPic = new Picture({tagName:'img', url:'./storage/pictures/pic4.jpg'});
    newSlide = new Slide(newPic, null);
    newTile = new Tile([newSlide]);
    tileList[0][4] = new TileView({container: contentContainer,
        id: "tile4",
        tile: newTile});

    newPic = new Picture({tagName:'img', url:'./storage/pictures/pic5.jpg'});
    newSlide = new Slide(newPic, null);
    newTile = new Tile([newSlide]);
    tileList[1][0] = new TileView({container: contentContainer,
        id: "tile5",
        tile: newTile});


    newTextField = new InputField({tagName: 'div', text: '密码'});
    newTextField2 = new InputField({tagName: 'div', text: '重复密码'});

    newInputForm = new InputForm({tagName:'div', formList: [newTextField, newTextField2]});
    newSlide = new Slide(newInputForm, null);
    newTile = new Tile([newSlide]);
    tileList[1][1] = new TileView({container: contentContainer,
        id: "tile6",
        tile: newTile});

    var newTagView = new TagList({tagName: 'div', text: '角色', tagList: ["摄影师", "驴友"]});
    newTextField = new InputField({tagName: 'div', text: '职业'});

    newInputForm = new InputForm({tagName: 'div', formList: [newTagView, newTextField]});
    newSlide = new Slide(newInputForm, null);
    newTile = new Tile([newSlide]);
    tileList[1][2] = new TileView({container: contentContainer,
        id: "tile7",
        tile: newTile});

    var newLevelDes = new LevelDes({tagName: 'div', left: '高富帅', right: '矮矬穷'});
    var newLevelDes2 = new LevelDes({tagName: 'div', left: '热情', right: '高冷'});
    newInputForm = new InputForm({tagName: 'div', formList: [newLevelDes, newLevelDes2]});
    newSlide = new Slide(newInputForm, null);
    newTile = new Tile([newSlide]);
    tileList[1][3] = new TileView({container: contentContainer,
        id: "tile8",
        tile: newTile});

    newPic = new Picture({tagName:'img', url:'./storage/pictures/pic7.jpg'});
    newSlide = new Slide(newPic, null);
    newTile = new Tile([newSlide]);
    tileList[1][4] = new TileView({container: contentContainer,
        id: "tile9",
        tile: newTile});

    newPic = new Picture({tagName:'img', url:'./storage/pictures/pic8.jpg'});
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

    newPic = new Picture({tagName:'img', url:'./storage/pictures/pic10.jpg'});
    newSlide = new Slide(newPic, null);
    newTile = new Tile([newSlide]);
    tileList[2][2] = new TileView({container: contentContainer,
        id: "tile12",
        tile: newTile});

    newPic = new Picture({tagName:'img', url:'./storage/pictures/pic11.jpg'});
    newSlide = new Slide(newPic, null);
    newTile = new Tile([newSlide]);
    tileList[2][3] = new TileView({container: contentContainer,
        id: "tile13",
        tile: newTile});

    newPic = new Picture({tagName:'img', url:'./images/next.jpg'});
    newSlide = new Slide(newPic, null);
    newTile = new Tile([newSlide]);
    tileList[2][4] = new TileView({container: contentContainer,
        id: "next",
        tile: newTile});
})

