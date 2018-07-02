package com.kitri.daily.board;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

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
	public void delBoard(int board_seq, String writer) {
		boardMapper = sqlSession.getMapper(BoardMapper.class);
		boardMapper.delete(board_seq, writer);
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
}
