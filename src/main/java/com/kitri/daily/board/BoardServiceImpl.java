package com.kitri.daily.board;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import com.kitri.daily.search.Hashtag;

@Component("boardService")
public class BoardServiceImpl implements BoardService {
	@Resource(name="sqlSession")//의존성 주입
	private SqlSession sqlSession;
	private BoardMapper boardMapper;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public void uploadBoard(Board b) {
		boardMapper = sqlSession.getMapper(BoardMapper.class);
		boardMapper.insert(b);
	}

	@Override
	public Board detailBoard(int board_seq) {
		boardMapper = sqlSession.getMapper(BoardMapper.class);
		return boardMapper.select(board_seq);
	}

	@Override
	public void editBoard(Board b) {
		boardMapper = sqlSession.getMapper(BoardMapper.class);
		boardMapper.update(b);
	}

	@Override
	public List<Board> getMyList(String id) {
		boardMapper = sqlSession.getMapper(BoardMapper.class);
		return boardMapper.myList(id);
	}

	@Override
	public List<Board> getNewsfeed(String id) {
		boardMapper = sqlSession.getMapper(BoardMapper.class);
		return boardMapper.newsfeed(id);
	}

	@Override
	public Board selectByid(String id){
		boardMapper = sqlSession.getMapper(BoardMapper.class);
		return boardMapper.selectById(id);
	}

	@Override
	public void insertHashtag(Hashtag h) {
		boardMapper = sqlSession.getMapper(BoardMapper.class);
		boardMapper.insertTag(h);
	}

	@Override
	public void deleteLikeSiren(int board_seq) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteHashtag(int board_seq) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteComment(int board_seq) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBoard(int board_seq) throws Exception {
		boardMapper = sqlSession.getMapper(BoardMapper.class);
		boardMapper.dellikeSiren(board_seq);
		boardMapper.delHashtag(board_seq);
		boardMapper.delComment(board_seq);
		boardMapper.delBoard(board_seq);
	}
}
