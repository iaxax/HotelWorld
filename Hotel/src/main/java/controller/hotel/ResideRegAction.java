package controller.hotel;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import service.intf.Hotel;
import vo.hotel.ResideVO;
import vo.result.ResultVO;

public class ResideRegAction extends ActionSupport {
        
        private static final long serialVersionUID = 4032861659245460545L;

        private String memberId;
        
        private String roomNum;
        
        private String registrant;
        
        private int days;
        
        private int roomPrice;
        
        private List<String> rooms;
        
        private Hotel hotel;
        
        private ResultVO result;

        public String getBookRoomsAtReside() {
                HttpServletRequest request = ServletActionContext.getRequest();
                HttpSession session = request.getSession();
                if (session == null) {
                        return "login";
                }
                
                rooms = hotel.getBookRooms(memberId);
                return SUCCESS;
        }
        
        public String reside() {
                HttpServletRequest request = ServletActionContext.getRequest();
                HttpSession session = request.getSession();
                if (session == null) {
                        return "login";
                }
                
                result = hotel.resideRegister(new ResideVO(
                                (String)session.getAttribute("id"), 
                                memberId, roomNum,
                                registrant, days
                ));
                return SUCCESS;
        }
        
        public String getPrice() {
                HttpServletRequest request = ServletActionContext.getRequest();
                HttpSession session = request.getSession();
                if (session == null) {
                        return "login";
                }
                
                roomPrice = hotel.getRoomPrice(
                                (String)session.getAttribute("id"), roomNum
                );
                return SUCCESS;
        }

        public String getMemberId() {
                return memberId;
        }

        public void setMemberId(String memberId) {
                this.memberId = memberId;
        }

        public List<String> getRooms() {
                return rooms;
        }

        public void setRooms(List<String> rooms) {
                this.rooms = rooms;
        }

        public Hotel getHotel() {
                return hotel;
        }

        public void setHotel(Hotel hotel) {
                this.hotel = hotel;
        }

        public ResultVO getResult() {
                return result;
        }

        public void setResult(ResultVO result) {
                this.result = result;
        }

        public String getRoomNum() {
                return roomNum;
        }

        public void setRoomNum(String roomNum) {
                this.roomNum = roomNum;
        }

        public String getRegistrant() {
                return registrant;
        }

        public void setRegistrant(String registrant) {
                this.registrant = registrant;
        }

        public int getDays() {
                return days;
        }

        public void setDays(int days) {
                this.days = days;
        }

        public int getRoomPrice() {
                return roomPrice;
        }

        public void setRoomPrice(int roomPrice) {
                this.roomPrice = roomPrice;
        }

}
