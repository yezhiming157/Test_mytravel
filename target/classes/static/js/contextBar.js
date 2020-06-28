var queryText;
function pageSkip(Index) {
	doqueryList(Index,queryText);
}

function getContextBar(page){
	var contentBar = '';
	if(page.currentPage==1){
		contentBar+='<li class="disabled"><a href="#">上一页</a></li>';
	}else{
		contentBar+='<li><a href="#" onclick="pageSkip('+(page.currentPage-1)+')" >上一页 </a></li>';
	}
	
	var beginIndex;
	var endIndex;
	if(page.totalPage<=5){
		beginIndex=1;
		endIndex=page.totalPage;
	}else{
		beginIndex=page.currentPage-2;
		endIndex=page.currentPage+2;
		if(beginIndex<1){
			beginIndex=1;
			endIndex=5;
		}
		if(endIndex>page.totalPage){
			beginIndex=page.totalPage-4;
			endIndex=page.totalPage;
		}
	}
	
		for(var i=beginIndex;i<=endIndex;i++){
		contentBar+='<li';
		if(page.currentPage==i){
			contentBar+=' class="active"';
		}
		contentBar+='><a href="#" onclick="pageSkip('+i+')">'+i+'</a></li>';
	}
		
	if(page.currentPage==page.totalPage){
		contentBar+='<li class="disabled"><a href="#">下一页</a></li>';
	}else{
		contentBar+='<li><a href="#" onclick="pageSkip('+(page.currentPage+1)+')">下一页 </a></li>';
	}
	$(".pagination").html(contentBar);
}