package com.kitri.daily.board;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.kitri.daily.friend.Relationship;
import com.kitri.daily.member.Member;
import com.kitri.daily.search.Hashtag;

@Controller
public class BoardController {
	String basePath = System.getProperty("catalina.home") + "\\webapps";

	@Resource(name = "boardService")
	private BoardService service;

	public void setService(BoardService service) {
		this.service = service;
	}

	@RequestMapping(value = "/board/form.do")
	void form() {

	}

	@RequestMapping(value = "/board/upload.do")
	public String upload(HttpServletRequest req, Board b) {
		String originPath = basePath + "\\board\\"; // 원본파일 경로
		String upfolder = basePath + "\\thumbnail\\"; // 썸네일 처리한 파일 경로
		MultipartFile file = b.getFile(); // form.jsp에서 선택한 파일 가져오기
		if (file != null && !file.equals("")) {
			File dir = new File(originPath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			// 파일 중복방지 처리
			String[] extension = file.getOriginalFilename().split("\\.");
			String FileType = extension[extension.length - 1];
			String img = b.getWriter() + "_" + System.currentTimeMillis() + "." + FileType;
			b.setImg(img); // img 경로 set
			File f = new File(originPath + "\\" + img);
			try {
				file.transferTo(f);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			// 썸네일 처리
			int thumbnail_width = 375;
			int thumbnail_height = 350;
			File dir2 = new File(upfolder);
			if (!dir2.exists()) {
				dir2.mkdirs();
			}
			try {
				BufferedImage srcImg = ImageIO.read(f); // 원본파일 읽어오기
				BufferedImage thumbImg;
				thumbImg = new BufferedImage(thumbnail_width, thumbnail_height, BufferedImage.TYPE_3BYTE_BGR);
				java.awt.Graphics2D g = thumbImg.createGraphics();
				g.drawImage(srcImg, 0, 0, thumbnail_width, thumbnail_height, null);
				File outFile = new File(upfolder + "\\" + img);
				ImageIO.write(thumbImg, "PNG", outFile); // 썸네일 파일 저장

			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		service.uploadBoard(b); // board테이블에 insert
		return "redirect:/board/tagInsert.do";
	}

	@RequestMapping(value = "/board/tagInsert.do")
	public String tagInsert(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		Member mem = (Member) session.getAttribute("memInfo");
		String id = mem.getId();
		System.out.println("id?" + id);
		Board board = service.selectByid(id);
		// 해시태그 처리
		if (board.getContent().contains("#")) {
			String block_yn = "N";
			String content = board.getContent(); // 기존에 board에 있는 내용을 가져온다.
			System.out.println("글내용: " + content);
			// 정규표현식을 이용한 해시태그 추출
			Pattern p = Pattern.compile("\\#([0-9a-zA-Z가-힣]*)");
			Matcher m = p.matcher(content);
			String extTag = null;
			// #이 붙은 문자열을 찾아서 insert
			while (m.find()) {
				extTag = ch_replace(m.group());
				if (extTag != null) {
					Hashtag h = new Hashtag(board.getBoard_seq(), extTag, block_yn);
					service.insertHashtag(h);
					System.out.println("해시태그 : " + extTag);
				}
			}
		}
		return "redirect:/board/myList.do";
	}

	public String ch_replace(String str) {
		str = StringUtils.replace(str, "-_+=!@#$%^&*()[]{}|\\;:'\"<>,.?/~`） ", "");
		if (str.length() < 1) {
			return null;
		}
		return str;
	}

	@RequestMapping(value = "/board/newsfeed.do", method = RequestMethod.GET)
	public void newsfeed(Model model, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView("board/newsfeed");
		HttpSession session = req.getSession(false);
		Member mem = (Member) session.getAttribute("memInfo");
		String id = mem.getId();
		List<Board> feedList = (ArrayList<Board>) service.getNewsfeed(id);
		model.addAttribute("feed", feedList);
	}

	@RequestMapping(value = "/board/del.do")
	public String delete(HttpServletRequest req, @RequestParam(value = "bseq") int bseq) throws Exception {
		HttpSession session = req.getSession(false);
		Member mem = (Member) session.getAttribute("memInfo");
		String id = mem.getId();
		service.deleteBoard(bseq);
		return "redirect:/board/myList.do";
	}

	@RequestMapping(value = "/board/updateBoard.do")
	public ModelAndView editBoard(@RequestParam(value = "bseq") int bseq, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView("board/editForm");
		Board update = service.detailBoard(bseq);
		mav.addObject("update", update);
		return mav;
	}

	@RequestMapping(value = "/board/edit.do")
	public String edit(Board b) {
		String originPath = basePath + "\\board\\"; // 원본파일 경로
		String upfolder = basePath + "\\thumbnail\\"; // 썸네일 처리한 파일 경로
		MultipartFile file = b.getFile(); // form.jsp에서 선택한 파일 가져오기
		if (file != null && !file.equals("")) {
			Board d = service.detailBoard(b.getBoard_seq());
			String del = originPath + d.getImg(); // 원본파일 경로와 파일명
			String del2 = upfolder + d.getImg(); // 원본파일 경로와 파일명
			File delete = new File(del);
			File delete2 = new File(del2);
			// 파일 중복방지 처리
			String[] extension = file.getOriginalFilename().split("\\.");
			String FileType = extension[extension.length - 1];
			String img = b.getWriter() + "_" + System.currentTimeMillis() + "." + FileType;
			b.setImg(img); // img 경로 set
			File f = new File(originPath + "\\" + img);

			try {
				file.transferTo(f); // 새로운 파일을 넣음
				delete.delete();
				delete2.delete();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			// 썸네일 처리
			int thumbnail_width = 375;
			int thumbnail_height = 350;
			File dir2 = new File(upfolder);
			if (!dir2.exists()) {
				dir2.mkdirs();
			}
			try {
				BufferedImage srcImg = ImageIO.read(f); // 원본파일 읽어오기
				BufferedImage thumbImg;
				thumbImg = new BufferedImage(thumbnail_width, thumbnail_height, BufferedImage.TYPE_3BYTE_BGR);
				java.awt.Graphics2D g = thumbImg.createGraphics();
				g.drawImage(srcImg, 0, 0, thumbnail_width, thumbnail_height, null);
				File outFile = new File(upfolder + "\\" + img);
				ImageIO.write(thumbImg, "PNG", outFile); // 썸네일 파일 저장

			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		service.editBoard(b);
		return "redirect:/board/post.do?bseq=" + b.getBoard_seq();
	}

	@RequestMapping(value = "/board/post.do")
	public ModelAndView detail(HttpServletRequest req, @RequestParam(value = "bseq") int bseq) {
		ModelAndView mav = new ModelAndView("board/post");
		HttpSession session = req.getSession(false);
		Member mem = (Member) session.getAttribute("memInfo");
		String id = mem.getId();
		Like like = new Like(bseq, id);
		Like l = service.getType(like);
		mav.addObject("l", l);
		Board b = service.detailBoard(bseq);
		List<Comment> coList = service.getComments(bseq);// 해당글의 코멘트 리스트들 가져오기.
		mav.addObject("b", b);
		mav.addObject("coList", coList);
		String upfolder = basePath + "\\thumbnail\\"; // img 가져올 파일 경로
		System.out.println("이미지~~~~~~!! " + b.getImg());
		String path = upfolder + b.getImg();
		System.out.println(path);
		mav.addObject("path", path);

		return mav;
	}

	@RequestMapping(value = "/board/myList.do")
	public ModelAndView list(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		Member mem = (Member) session.getAttribute("memInfo");
		String id = mem.getId();
		List<Board> boardlist = (ArrayList<Board>) service.getMyList(id);
		/*
		 * Timer t = new Timer(true); TimerTask m_task = new TimerTask() {
		 * 
		 * @Override public void run() { System.out.println("공개 게시물2 : " + boardlist);
		 * for(int i=0; i<boardlist.size(); i++) { System.out.println("공개 게시물"+i+" : " +
		 * boardlist.get(i).getPublic_yn());
		 * if(boardlist.get(i).getPublic_yn().trim().equals("yd") ||
		 * boardlist.get(i).getPublic_yn().trim().equals("nd")) {
		 * System.out.println("공개 게시물 : " + boardlist.get(i).getPublic_yn()); String yn
		 * = boardlist.get(i).getPublic_yn().substring(0, 1);
		 * boardlist.get(i).setPublic_yn(yn); service.updelay(boardlist.get(i)); } } }
		 * };
		 * 
		 * t.schedule(m_task, 10000);
		 */
		ModelAndView mav = new ModelAndView("board/myList");
		mav.addObject("list", boardlist);
		return mav;
	}


   @RequestMapping(value = "/board/delType.do")
   public String delType (HttpServletRequest req ,@RequestParam(value="bseq") int bseq) {
	   HttpSession session = req.getSession(false);
	   Member mem  = (Member) session.getAttribute("memInfo");
	   String id = mem.getId();
	   Like like = new Like(bseq, id);
	   service.delType(like);
	   return "redirect:/board/post.do?bseq="+bseq;
   }
   
   @RequestMapping(value = "/board/like.do")
   public String like (HttpServletRequest req ,@RequestParam(value="bseq") int bseq) {
	   HttpSession session = req.getSession(false);
	   Member mem  = (Member) session.getAttribute("memInfo");
	   String id = mem.getId();
	   Like like = new Like(bseq, id);
	   service.addLike(like);
	   return "redirect:/board/post.do?bseq="+bseq;
   }
   
   @RequestMapping(value = "/board/siren.do")
   public String siren (HttpServletRequest req ,@RequestParam(value="bseq") int bseq) {
	   HttpSession session = req.getSession(false);
	   Member mem  = (Member) session.getAttribute("memInfo");
	   String id = mem.getId();
	   Like like = new Like(bseq, id);
	   service.addSiren(like);
	   return "redirect:/board/post.do?bseq="+bseq;
   }
   
   @RequestMapping(value = "/board/friList.do")
   public ModelAndView friProfile(HttpServletRequest req ,
		   						@RequestParam(value="writer") String writer) {
	   HttpSession session = req.getSession(false);
	   Member mem  = (Member) session.getAttribute("memInfo");
	   String id = mem.getId();
	   System.out.println("작가 : " + writer + " id : " + id);
	   Board board = new Board(writer, id);
	   List<Board> list = (ArrayList<Board>) service.getList(board);
	   Member fri = service.friend(writer);
	   session.setAttribute("friendId", writer);
	   ModelAndView mav = new ModelAndView("board/friList");
	   mav.addObject("list", list);
	   mav.addObject("fri", fri);
	   
	   Relationship relation = new Relationship(id, writer);
	   ArrayList<Integer> count =  service.FriendprofileCount(writer);
	   String check = service.checkRelation(relation);
	   session.setAttribute("check", check);//  나와 상대방의 관계
	   session.setAttribute("friendfollowerCount", count.get(0));
	   session.setAttribute("friendfollowingCount",count.get(1));
	   session.setAttribute("friendsubscribeCount", count.get(2));
	   return mav;
  	}
}