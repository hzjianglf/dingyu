	
        var position = 1;
        
        function next() {	
            position -= 23;
            if (position <= -69)
                position = -68;	
            update();
			
        }
        function prev() {	
            position += 23;
            if (position > 1)
                position = 1;	
            update();
			
        }
		
		

        function update() {
            var pan = document.getElementById("pan");
            pan.style.OTransform = "translateX(" + position + "%)";
            pan.style.MozTransform = "translateX(" + position + "%)";
            pan.style.WebkitTransform = "translateX(" + position + "%)";
			$("#listSmall ul li").removeClass('addBor');
			
			if(position == 1){
				$("#listSmall ul li").removeClass('addBor');
			    $("#listSmall ul li img#img01").parentsUntil().addClass('addBor');	
				
				}
			else if(position == -22){
				
			$("#listSmall ul li").removeClass('addBor');
			    $("#listSmall ul li img#img02").parentsUntil().addClass('addBor');	
				}
			else if(position == -45){
				$("#listSmall ul li").removeClass('addBor');
			    $("#listSmall ul li img#img03").parentsUntil().addClass('addBor');	
				}
			else if(position == -68){
				$("#listSmall ul li").removeClass('addBor');
			    $("#listSmall ul li img#img04").parentsUntil().addClass('addBor');	
				}
			
        }
        $(function() {
			$("#listSmall ul li").last().css("margin-right",0);
			$("#img01").parentsUntil().addClass('addBor');
			pan.style.OTransform = "translateX(" + 1 + "%)";
            pan.style.MozTransform = "translateX(" + 1 + "%)";
            pan.style.WebkitTransform = "translateX(" + 1 + "%)";
            $(window).bind("swipeleft", next);
            $(window).bind("swiperight", prev);
			
		
				
			
			
			$("#listSmall ul li img").click( function(){
			    var Namb = $("#listSmall ul li img").index($(this));
			    $("#listSmall ul li").removeClass('addBor');
			    $(this).parentsUntil().addClass('addBor');	
			    pan.style.OTransform = "translateX(" + (-23*Namb+1) + "%)";
                pan.style.MozTransform = "translateX(" + (-23*Namb+1) + "%)";
                pan.style.WebkitTransform = "translateX(" + (-23*Namb+1) + "%)";			
			});	
			
			
            $(window).bind("keydown", function(event) {
                if (event.which==37)
                    prev();
                else if (event.which==39)
                    next();
            });
            $("img").bind("dragstart", function(ev) { ev.preventDefault(); });
        });