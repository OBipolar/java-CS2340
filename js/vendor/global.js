/*
 **  Project STP
 **  global.js
 **  all class and constants definition
 */

function XOR(a, b) {
    return ( a || b ) && !( a && b );
}


// All Models

// Model for a single slide, could be both one-sided or double-sided
// @param frontSlide, front slide
// @param backSlide, optional, back slide
Slide = Backbone.Model.extend({
    initialize: function(frontSlide, backSlide) {
        this.front = frontSlide;
        this.back  = backSlide;
    },

    hasBack: function() {
        return (this.back.el != undefined);
    }
})

// Model for a single tile, contains several slides
// @param slides, optional, all of the slides in this tile
Tile = Backbone.Model.extend({
    initialize: function(slides) {
        this.slides = slides || [];
    },

    addSlide: function(newSlide) {
        this.slides.push(newSlide);
    }
})


// All Views
// View Element for one side of a slide
// @param opt, options
SlideElement = Backbone.View.extend({
    initialize: function(opt) {
        this.content = opt.content;
        this.width = opt.width || "168px";
        this.height = opt.height || "168px";

        $(this.el).css('width', this.width);
        $(this.el).css('height', this.height);
        $(this.el).css('position', 'absolute');
        $(this.el).html(this.content);
    }
})

// View Element for one picture of a slide
// @param opt, options
Picture = SlideElement.extend({
    initialize: function(opt) {
        Picture.__super__.initialize.call(this, opt);
        $(this.el).attr('src', opt.url);
    }
})


// View Element for input forms
// @param opt, options
InputForm = SlideElement.extend({
    initialize: function(opt) {
        InputForm.__super__.initialize.call(this, opt);
        this.formList = [];
        $(this.el).css('backgroundColor','#ecc12b');
        for(var i = 0; i < opt.formList.length; i++) {
            this.formList[i] = opt.formList[i];
            $(this.formList[i].el).css('height', 80 / opt.formList.length + "%");
            $(this.el).append($(this.formList[i].el));
        }
    }
})

// View Element for a single input in an input form
// @param opt, options
InputFormElement = Backbone.View.extend({
    initialize: function(opt) {
        $(this.el).css('width','100%');
    }
})


// View Element for an input field
InputField = InputFormElement.extend({
    initialize: function(opt) {
        InputField.__super__.initialize.call(this, opt);
        this.content = opt.text;
        this.textE = $(document.createElement('div'));
        this.textField = $(document.createElement('input'));
        this.textE.css('width','80%');
        this.textE.css('margin','6% 8% 1% 8%');
        this.textE.css('height', '40%');
        this.textE.css('color','#fff');
        this.textE.html(this.content);
        this.textField.css('width', '80%');
        this.textField.css('margin','1% 8% 6% 8%');
        this.textField.css('padding','1px 5px');
        this.textField.css('height', '40%');
        this.textField.css('backgroundColor','#FFEDC1');
        this.textField.attr('type','input');
        $(this.el).append(this.textE);
        $(this.el).append(this.textField);
    }
})

// View Element for a tag list
TagList = InputFormElement.extend({
    initialize: function(opt) {
        TagList.__super__.initialize.call(this, opt);
        this.content = opt.text;
        this.tagList = opt.tagList;
        this.textE = $(document.createElement('div'));
        this.tagE = $(document.createElement('div'));
        this.textE.css('width','80%');
        this.textE.css('margin','6% 8% 1% 8%');
        this.textE.css('height', '40%');
        this.textE.css('color','#fff');
        this.textE.html(this.content);
        this.tagE.css('width', '80%');
        this.tagE.css('margin','1% 8% 6% 8%');
        this.tagE.css('height', '40%');
        for(var i = 0; i < this.tagList.length; i++) {
            var newTag = $(document.createElement('div'));
            newTag.html(this.tagList[i]);
            newTag.css('float','left');
            newTag.css('color','#8f8f8f');
            newTag.css('backgroundColor','#FFEDC1');
            newTag.css('padding','2px');
            newTag.css('margin','2px 2px');
            newTag.css('cursor','pointer');
            this.tagE.append(newTag);
        }
        $(this.el).append(this.textE);
        $(this.el).append(this.tagE);
    }
})

// View Element for a level descriptor
LevelDes = InputFormElement.extend({
    initialize: function(opt) {
        LevelDes.__super__.initialize.call(this, opt);
        var self = this;
        this.leftContent = opt.left;
        this.rightContent = opt.right;
        this.textE = $(document.createElement('div'));
        this.levelE = $(document.createElement('div'));
        this.textE.css('width','80%');
        this.textE.css('margin','6% 8% 1% 8%');
        this.textE.css('height', '30%');
        this.textE.css('color','#fff');
        this.textE.css('position','relative');
        this.leftE = $(document.createElement('div'));
        this.rightE = $(document.createElement('div'));
        this.leftE.html(this.leftContent);
        this.rightE.html(this.rightContent);
        this.leftE.css('position','absolute');
        this.rightE.css('position','absolute');
        this.leftE.css('left','2px');
        this.rightE.css('right','2px');
        this.textE.append(this.leftE);
        this.textE.append(this.rightE);
        this.levelE.css('position','relative');
        this.levelE.css('width','80%');
        this.levelE.css('margin','1% 8% 1% 8%');
        this.levelE.css('height', '50%');
        this.levelBlockE = [];
        for(var i = 0; i < 9; i++) {
            var blockE = $(document.createElement('div'));
            blockE.css('position','absolute');
            blockE.css('left', i * 11.25 + '%');
            blockE.css('width','9%');
            blockE.css('height','100%');
            blockE.css('backgroundColor','#FFEDC1');
            blockE.attr('num',i);
            blockE.click(function(){
                var curr = $(this).attr('num');
                for(var j = 0; j < 9; j++)
                    self.levelBlockE[j].css('backgroundColor','#FFEDC1')
                if(curr < 4) {
                    for(var j = curr; j < 4; j++)
                        self.levelBlockE[j].css('backgroundColor','#F99C37');
                } else {
                    for(var j = 4; j <= curr; j++)
                        self.levelBlockE[j].css('backgroundColor','#F99C37');
                }
            })
            this.levelBlockE[i] = blockE;
            this.levelE.append(blockE);
        }
        $(this.el).append(this.textE);
        $(this.el).append(this.levelE);
    }
})


// View Element for one tile
// @param opt, options
TileView = Backbone.View.extend({
    tagName: 'div',
    className: 'tile-wrapper',
    initialize: function(opt) {
        this.width = opt.width || "168px";
        this.height = opt.height || "168px";
        this.id = opt.id;
        this.tile = opt.tile;
        this.currentSlide = 0;
        this.slides = [];
        $(this.el).css('width', this.width);
        $(this.el).css('height', this.height);
        $(this.el).attr('id', this.id);
        opt.container.append($(this.el));

        for(var i = 0; i < this.tile.slides.length; i++) {
            var newSlide = new SlideView({slide: this.tile.slides[i]});
            this.slides[i] = newSlide;
            $(this.el).append($(newSlide.el));
            if(i == 0)
                $(newSlide.el).css('display','block');
        }
    },

    playTo: function(index) {
        this.$('.slides').css('display','none');
        $(this.slides[index].el).slideDown(1000);
    }
})


// View Element for slider inside one tile
// @param opt, options
SlideView = Backbone.View.extend({
    tagName: 'div',
    className: 'slides',
    initialize: function(opt) {
        $(this.el).append($(opt.slide.front.el));
        if(opt.slide.hasBack()) {
            $(this.el).addClass('flip-wrapper');
            $(this.el).append($(opt.slide.back.el));
        }
        $(this.el).css('position','relative');
    }
})
