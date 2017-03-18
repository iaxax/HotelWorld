package controller.hotel;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import service.intf.Hotel;
import vo.hotel.BranchRequestVO;
import vo.hotel.InfoRequestVO;
import vo.hotel.PlanRequestVO;

public class HotelInformAction extends ActionSupport {
        
        private Hotel hotel;
        
        private List<BranchRequestVO> branchList;
        
        private List<PlanRequestVO> planList;
        
        private List<InfoRequestVO> infoList;
        
        public String getBranchInform() {
                HttpServletRequest request = ServletActionContext.getRequest();
                HttpSession session = request.getSession();
                if (session == null) {
                        return "login";
                }
                
                branchList = hotel.getAllBranchRequest((String)session.getAttribute("id"));
                return SUCCESS;
        }

        public String getInfoInform() {
                HttpServletRequest request = ServletActionContext.getRequest();
                HttpSession session = request.getSession();
                if (session == null) {
                        return "login";
                }
                
                infoList = hotel.getAllInfoRequest((String)session.getAttribute("id"));
                return SUCCESS;
        }
        
        public String getPlanInform() {
                HttpServletRequest request = ServletActionContext.getRequest();
                HttpSession session = request.getSession();
                if (session == null) {
                        return "login";
                }
                
                planList = hotel.getAllPlanRequest((String)session.getAttribute("id"));
                return SUCCESS;
        }

        public Hotel getHotel() {
                return hotel;
        }

        public void setHotel(Hotel hotel) {
                this.hotel = hotel;
        }

        public List<BranchRequestVO> getBranchList() {
                return branchList;
        }

        public void setBranchList(List<BranchRequestVO> branchList) {
                this.branchList = branchList;
        }

        public List<PlanRequestVO> getPlanList() {
                return planList;
        }

        public void setPlanList(List<PlanRequestVO> planList) {
                this.planList = planList;
        }

        public List<InfoRequestVO> getInfoList() {
                return infoList;
        }

        public void setInfoList(List<InfoRequestVO> infoList) {
                this.infoList = infoList;
        }
        
}
