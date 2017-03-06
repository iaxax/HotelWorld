package controller.hotel;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import service.intf.Hotel;
import vo.hotel.CancelRoomVO;
import vo.result.ResultVO;

public class BookCancelAction extends ActionSupport {

        private static final long serialVersionUID = 509646434955970618L;

        private String hotelName;
        
        private String room;
        
        private ResultVO result;
        
        private Hotel hotel;
        
        private List<String> bookList;
        
        public String cancel() {
                HttpServletRequest request = ServletActionContext.getRequest();
                HttpSession session = request.getSession();
                if (session == null) {
                        return "login";
                }
                result = hotel.cancelRoom(
                                new CancelRoomVO((String)session.getAttribute("id"), hotelName, room)
                );
                return SUCCESS;
        }
        
        public String getBookRooms() {
                HttpServletRequest request = ServletActionContext.getRequest();
                HttpSession session = request.getSession();
                if (session == null) {
                        return "login";
                }
                
                bookList = hotel.getBookRooms((String)session.getAttribute("id"));
                return SUCCESS;
        }

        public void setHotelName(String hotelName) {
                this.hotelName = hotelName;
        }

        public void setRoom(String room) {
                this.room = room;
        }

        public void setResult(ResultVO result) {
                this.result = result;
        }

        public ResultVO getResult() {
                return result;
        }

        public void setHotel(Hotel hotel) {
                this.hotel = hotel;
        }

        public List<String> getBookList() {
                return bookList;
        }
        
}
