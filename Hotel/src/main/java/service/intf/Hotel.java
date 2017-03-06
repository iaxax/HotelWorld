package service.intf;

import java.util.List;
import java.util.Map;

import vo.hotel.BookRoomVO;
import vo.hotel.CancelRoomVO;
import vo.result.ResultVO;

public interface Hotel {

        Map<String, List<String>> getRoomInfo();
        
        ResultVO bookRoom(BookRoomVO vo);
        
        ResultVO cancelRoom(CancelRoomVO vo);
        
        List<String> getBookRooms(String id);
}
