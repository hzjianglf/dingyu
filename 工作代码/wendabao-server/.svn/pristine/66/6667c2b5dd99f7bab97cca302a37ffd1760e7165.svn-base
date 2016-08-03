/**
 * 显示分页导航
 * @param data 分页返回信息
 * @returns
 */	
function showNav(data){
		
		var lastPageStartRow = data.startRow-data.pageNum;
		
		if(lastPageStartRow <0){
			lastPageStartRow =0;
		}
		
		
		var nextPageStartRow = data.pageSize*data.pageNum+1;
		
		if(nextPageStartRow > data.total-1){
			nextPageStartRow = data.total-1;
		}
		
		var preBtn="";
		if(data.hasPreviousPage == false){
			preBtn="class=\"disabled\"";
		}
		
		var nextBtn="";
		if(data.hasNextPage == false){
			nextBtn="class=\"disabled\"";
		}
		var navHtml = [
		              
		"<span class=\"data-nav-info\" >总记录数："+data.total+" 页数："+data.pageNum+"/"+data.pages+"</span>",
		"  <nav class=\"data-nav\">	",
		 "	  <ul class=\"pagination\" >",
		 "	    <li "+preBtn+">",
		 "	      <a href=\"javascript:toPage("+lastPageStartRow+")\" aria-label=\"Previous\">",
		 "	        <span aria-hidden=\"true\" >上一页</span>",
		 "	      </a>",
		 "	    </li>			    ",
		 "		 <li "+nextBtn+">",
		 "		      <a href=\"javascript:toPage("+nextPageStartRow+")\" aria-label=\"Next\">",
		 "		        <span aria-hidden=\"true\">下一页</span>",
		 "		      </a>",
		 "		    </li>",
		 "		  </ul>",
		 "		</nav>	"].join("");
		
		return navHtml;
		
	}