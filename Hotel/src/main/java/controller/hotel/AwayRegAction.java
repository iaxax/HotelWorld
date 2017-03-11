package controller.hotel;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import service.intf.Hotel;
import vo.hotel.AwayVO;
import vo.result.ResultVO;

public class AwayRegAction extends ActionSupport {
        
        private static final long serialVersionUID = 2117604120872410589L;

        private String roomNum;
        
        private String idNum;
        
        private ResultVO result;
        
        private Hotel hotel;
        
        private List<String> rooms;

        public String away() {
                HttpServletRequest request = ServletActionContext.getRequest();
                HttpSession session = request.getSession();
                if (session == null) {
                        return "login";
                }
                
                
                result = hotel.awayRegister(new AwayVO(
                                (String)session.getAttribute("id"),
                                roomNum, idNum
                ));
                return SUCCESS;
        }
        
        public String getResideRooms() {
                HttpServletRequest request = ServletActionContext.getRequest();
                HttpSession session = request.getSession();
                if (session == null) {
                        return "login";
                }
                
                rooms = hotel.getResideRooms((String)session.getAttribute("id"), idNum);
                return SUCCESS;
        }

        public String getRoomNum() {
                return roomNum;
        }

        public void setRoomNum(String roomNum) {
                this.roomNum = roomNum;
        }

        public ResultVO getResult() {
                return result;
        }

        public void setResult(ResultVO result) {
                this.result = result;
        }

        public Hotel getHotel() {
                return hotel;
        }

        public void setHotel(Hotel hotel) {
                this.hotel = hotel;
        }

        public String getIdNum() {
                return idNum;
        }

        public void setIdNum(String idNum) {
                this.idNum = idNum;
        }

        public List<String> getRooms() {
                return rooms;
        }

        public void setRooms(List<String> rooms) {
                this.rooms = rooms;
        }
        
}
