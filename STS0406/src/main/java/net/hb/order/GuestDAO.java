package net.hb.order;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GuestDAO { //모델1 = 저장고(컨트롤 저장고 xml문서)
		//class="org.mybatis.spring.SqlSessionTemplate"
		
		@Autowired
		SqlSessionTemplate temp;
		//모델1 = 저장고 temp필드는 board.xml문서 id접근
		
		public void dbInsert(GuestDTO dto) {
			temp.insert("guest.add", dto);
			System.out.println("guest테이블 저장");
		}
		
		public List<GuestDTO> dbSelect() {
//			List list = temp.selectList("board.selectAll");
			//board.selectAll
			return temp.selectList("guest.selectAll");
		}

		public int dbCoun() {
			return temp.selectOne("guest.countAll");
		}
		
		public GuestDTO dbDetail(int data) {
			return temp.selectOne("guest.detail",data);
		}
		
		public void dbDelete(int data) {
			temp.delete("guest.delete",data);
			System.out.println(data + "번 삭제 완료");
		}

		public void dbUpdate(GuestDTO dto) {
			temp.update("guest.update",dto);  
		}
	
}
