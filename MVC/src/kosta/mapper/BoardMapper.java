package kosta.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import kosta.model.Board;
import kosta.model.Reply;
import kosta.model.Search;

public interface BoardMapper {
	int insertBoard(Board board);
	List<Board> listBoard(Search search,RowBounds row); //mybatis에서 처리하는 것 이므로 xml에서 파라미터로 굳이 넣지않아도 된다.
	Board detailBoard(int seq);
	int countBoard(Search search);
	void deleteBoard(int seq);
	int insertReply(Reply reply);
	List<Reply> listReply(int seq);
}
