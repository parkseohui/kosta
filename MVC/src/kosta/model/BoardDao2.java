package kosta.model;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kosta.mapper.BoardMapper;
//dao는 메소드하나당 쿼리하나!!! service는 쿼리를 여러개 실행하기 위해서!!
public class BoardDao2 {
	private static BoardDao2 dao = new BoardDao2();
	
	public static BoardDao2 getInstance() {
		return dao;
	}
	
	public SqlSessionFactory getSqlSessionFactory() {
		String resource = "mybatis-config.xml";
		InputStream in = null;
		
		try {
			in = Resources.getResourceAsStream(resource);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new SqlSessionFactoryBuilder().build(in);
		
	}
	
	public int insertBoard(Board board) {
		int re = -1;
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		
		try {
			re = sqlSession.getMapper(BoardMapper.class).insertBoard(board);
			if(re > 0) {
				sqlSession.commit();
			}else {
				sqlSession.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
		
		return re;
	}
	
	public List<Board> listBoard(Search search,int startRow){
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		List<Board> list = null;
		try {
			list = sqlSession.getMapper(BoardMapper.class).listBoard(search,new RowBounds(startRow, 2));
			//list = sqlSession.selectList("kosta.mapper.BoardMapper.listBoard");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
		
		return list;
	}
		public Board detailBoard(int seq) {
			SqlSession sqlSession = getSqlSessionFactory().openSession();
			Board board = null;
			try {
				board=sqlSession.getMapper(BoardMapper.class).detailBoard(seq);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if(sqlSession != null) {
					sqlSession.close();
				}
			}
			return board;
		}
		public int countBoard(Search search) {
			SqlSession sqlSession = getSqlSessionFactory().openSession();
			int re=0;
			try {
				re=sqlSession.getMapper(BoardMapper.class).countBoard(search);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				sqlSession.close();
			}
			return re;
		}
		
		public void deleteBoard(int seq) {
			System.out.println("인생");
			SqlSession sqlSession = getSqlSessionFactory().openSession();
			try {
				System.out.println("delete SEQ: "  + seq);
				 sqlSession.getMapper(BoardMapper.class).deleteBoard(seq);
				 sqlSession.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(sqlSession != null) {
					sqlSession.close();
				}
			}
			
		}
		public int insertReply(Reply reply) {
			SqlSession sqlSession = getSqlSessionFactory().openSession();
			int re=0;
			try {
				re=sqlSession.getMapper(BoardMapper.class).insertReply(reply);
				if(re > 0) {
					sqlSession.commit();
				}else {
					sqlSession.rollback();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(sqlSession != null) {
					sqlSession.close();
				}
			}
			return re;
		}
		
		public List<Reply> listReply(int seq) {
			List<Reply> list=null;
			SqlSession sqlSession = getSqlSessionFactory().openSession();
			try {
			list=sqlSession.getMapper(BoardMapper.class).listReply(seq);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(sqlSession != null) {
					sqlSession.close();
				}
			}
			return list;
		}
}













