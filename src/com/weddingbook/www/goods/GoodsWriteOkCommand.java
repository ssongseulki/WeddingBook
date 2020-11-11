package com.weddingbook.www.goods;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.weddingbook.www.command.Command;
import com.weddingbook.www.dao.GoodsDao;
import com.weddingbook.www.dto.GoodsDto;

public class GoodsWriteOkCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MultipartRequest multi = null;
		String path = request.getSession().getServletContext().getRealPath("IMG_Goods");
		
		int size = 1024 * 1024 * 10; //10M 최대사이즈
		String file = "";
		String oriFile = "";
		String[] fileNameArray = new String[5];
		
		try
		{
			multi = new MultipartRequest(request, path, size, "utf-8", new DefaultFileRenamePolicy());
			Enumeration files = multi.getFileNames();
			int i=0;
			while(files.hasMoreElements())
			{
				String str = (String)files.nextElement();
				file = multi.getFilesystemName(str);
				oriFile = multi.getOriginalFileName(str);
				
				if(file == null)
				{
					fileNameArray[i] = null;
				}
				else
				{
					fileNameArray[i] = request.getContextPath() + "/IMG_Goods/" + file;
				}
				i++;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		GoodsDao goodsDao = GoodsDao.getGoodsDao();
		GoodsDto goodsDto = new GoodsDto();
		
		goodsDto.setGoodsSDM(multi.getParameter("goodsSDM"));
		goodsDto.setGoodsName(multi.getParameter("goodsName"));
		goodsDto.setGoodsPosition(multi.getParameter("goodsPosition"));
		goodsDto.setGoodsPromotion(multi.getParameter("goodsPromotion"));
		goodsDto.setGoodsInfo(multi.getParameter("goodsInfo"));
		goodsDto.setGoodsBusinessHours(multi.getParameter("goodsBusinessHours"));
		goodsDto.setGoodsBusinessTel(multi.getParameter("goodsBusinessTel"));
		goodsDto.setGoodsBusinessAdd(multi.getParameter("goodsBusinessAdd"));
		goodsDto.setGoodsHomepageAdd(multi.getParameter("goodsHomepageAdd"));
		
		for (int i = 0; i < fileNameArray.length; i++)
		{
			switch (i) 
			{
				case 0: goodsDto.setGoodsFile5(fileNameArray[i]); break;
				case 1: goodsDto.setGoodsFile4(fileNameArray[i]); break;
				case 2: goodsDto.setGoodsFile3(fileNameArray[i]); break;
				case 3: goodsDto.setGoodsFile2(fileNameArray[i]); break;
				case 4: goodsDto.setGoodsFile1(fileNameArray[i]); break;
			}
		}
		goodsDao.writeOkDao(goodsDto);
	}
}
