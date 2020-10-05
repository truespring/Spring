package kr.hkit.exam09.test;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import kr.hkit.exam09.BoardVO;
import kr.hkit.exam09.Query;

class QueryTest {

	@BeforeAll
	static void start() {
		Query.createTable();
	}
	
	@AfterAll
	static void end() {
		Query.dropTable();
	}
	
	@BeforeEach
	void before() {
		Query.boardDelete(0);
		
		BoardVO bv1 = new BoardVO();
		bv1.setBtitle("타이틀1");
		bv1.setBcontent("내용1");
		Query.boardInsert(bv1);
		
		BoardVO bv2 = new BoardVO();
		bv2.setBtitle("타이틀2");
		bv2.setBcontent("내용2");
		Query.boardInsert(bv2);
	}
	
	@Test // getAllBoardList 에 에러 3개
	void testA() {
		List<BoardVO> list = Query.getAllBoardList();
		Assert.assertEquals(2, list.size());
		
		BoardVO vo1 = list.get(0);
		Assert.assertEquals("타이틀2", vo1.getBtitle());
		Assert.assertEquals("내용2", vo1.getBcontent());
		
		BoardVO vo2 = list.get(1);
		Assert.assertEquals("타이틀1", vo2.getBtitle());
		Assert.assertEquals("내용1", vo2.getBcontent());
	}
	
	@Test // getBoardDetail 에 에러 3개
	void testB() {
		List<BoardVO> list = Query.getAllBoardList();
		BoardVO vo1 = list.get(0);
		Query.boardDelete(vo1.getBid());
		BoardVO vo1Db = Query.getBoardDetail(vo1.getBid());
		Assert.assertEquals(0, vo1Db.getBid());
		Assert.assertNull(vo1Db.getBtitle());
		Assert.assertNull(vo1Db.getBcontent());
		
		Assert.assertEquals(1, Query.getAllBoardList().size());

		BoardVO vo2 = list.get(1);
		Query.boardDelete(vo2.getBid());
		BoardVO vo2Db = Query.getBoardDetail(vo2.getBid());
		Assert.assertEquals(0, vo2Db.getBid());
		Assert.assertNull(vo2Db.getBtitle());
		Assert.assertNull(vo2Db.getBcontent());
		
		Assert.assertEquals(0, Query.getAllBoardList().size());
	}
	
	@Test
	void testC() {
		List<BoardVO> list = Query.getAllBoardList();
		int bid = list.get(0).getBid(); // 1
		BoardVO vo1 = new BoardVO();
		vo1.setBid(bid);
		vo1.setBtitle("bid1번 제목");
		vo1.setBcontent("bid1번 내용");
		Query.boardUpdate(vo1);
		BoardVO vo1Db = Query.getBoardDetail(bid);
		Assert.assertEquals(vo1.getBtitle(), vo1Db.getBtitle());
		Assert.assertEquals(vo1.getBcontent(), vo1Db.getBcontent());
		
		bid = list.get(1).getBid(); // 2
		BoardVO vo2 = new BoardVO();
		vo2.setBid(bid);
		vo2.setBtitle("bid2번 제목");
		vo2.setBcontent("bid2번 내용");
		Query.boardUpdate(vo2);
		BoardVO vo2Db = Query.getBoardDetail(bid);
		Assert.assertEquals(vo2.getBtitle(), vo2Db.getBtitle());
		Assert.assertEquals(vo2.getBcontent(), vo2Db.getBcontent());
	}

}
