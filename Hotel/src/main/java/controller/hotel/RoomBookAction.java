package controller.hotel;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import service.intf.Hotel;
import vo.hotel.BookRoomVO;
import vo.result.ResultVO;

public class RoomBookAction extends ActionSupport {

        private static final long serialVersionUID = -1010471410453824831L;

        private String hotelName;
        
        private String room;
        
        private int days;
        
        private Map<String, List<String>> roomInfo;
        
        private ResultVO result;
        
        private Hotel hotel;
        
        public String book() {
                HttpServletRequest request = ServletActionContext.getRequest();
                HttpSession session = request.getSession();
                if (session == null) {
                        return "login";
                }
                
                result = hotel.bookRoom(new BookRoomVO(
                                (String)session.getAttribute("id"), hotelName, room, days
                ));
                return SUCCESS;
        }
        
        public String getInfo() {
                HttpServletRequest request = ServletActionContext.getRequest();
                HttpSession session = request.getSession();
                if (session == null) {
                        return "login";
                }
                        
                roomInfo = hotel.getRoomInfo();
                return SUCCESS;
        }
        
        public String getHotelName() {
                return hotelName;
        }

        public void setHotelName(String hotelName) {
                this.hotelName = hotelName;
        }

        public Hotel getHotel() {
                return hotel;
        }

        public void setHotel(Hotel hotel) {
                this.hotel = hotel;
        }

        public String getRoom() {
                return room;
        }

        public void setRoom(String room) {
                this.room = room;
        }

        public int getDays() {
                return days;
        }

        public void setDays(int days) {
                this.days = days;
        }

        public ResultVO getResult() {
                return result;
        }

        public void setResult(ResultVO result) {
                this.result = result;
        }

        public Map<String, List<String>> getRoomInfo() {
                return roomInfo;
        }

        public void setRoomInfo(Map<String, List<String>> roomInfo) {
                this.roomInfo = roomInfo;
        }
        
}
