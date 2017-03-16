package controller.member;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import service.intf.Member;
import vo.member.BookRecordVO;
import vo.member.CancelRecordVO;
import vo.member.MemberInfoVO;
import vo.member.ModifyInfoVO;
import vo.member.ResideRecordVO;
import vo.result.ResultVO;

public class MemberDataAction extends ActionSupport {
        
        private static final long serialVersionUID = 2994949995906550259L;

        private Member member;
        
        private List<Map<String, Integer>> data;
        
        private MemberInfoVO memberInfo;
        
        private String name;
        
        private String phone;
        
        private ResultVO result;
        
        private List<ResideRecordVO> resideRecords;
        
        private List<BookRecordVO> bookRecords;
        
        private List<CancelRecordVO> cancelRecords;
        
        private Map<String, Integer> consumeData;

        public String getMemberData() {
                HttpServletRequest request = ServletActionContext.getRequest();
                HttpSession session = request.getSession();
                if (session == null) {
                        return "login";
                }
                
                data = member.getMemberData((String)session.getAttribute("id"));
                return SUCCESS;
        }
        
        public String getBasicInfo() {
                HttpServletRequest request = ServletActionContext.getRequest();
                HttpSession session = request.getSession();
                if (session == null) {
                        return "login";
                }
                
                memberInfo = member.getBasicInfo((String)session.getAttribute("id"));
                return SUCCESS;
        }
        
        public String getMemberFinanceStat() {
                HttpServletRequest request = ServletActionContext.getRequest();
                HttpSession session = request.getSession();
                if (session == null) {
                        return "login";
                }
                
                consumeData = member.getCosumeStat((String)session.getAttribute("id"));
                return SUCCESS;
        }
        
        public String modifyInfo() {
                HttpServletRequest request = ServletActionContext.getRequest();
                HttpSession session = request.getSession();
                if (session == null) {
                        return "login";
                }
                
                result = member.modifyInfo(new ModifyInfoVO(
                                (String)session.getAttribute("id"),
                                name, phone
                ));
                return SUCCESS;
        }
        
        public String getMemberResideRecord() {
                HttpServletRequest request = ServletActionContext.getRequest();
                HttpSession session = request.getSession();
                if (session == null) {
                        return "login";
                }
                
                resideRecords = member.getResideRecords((String)session.getAttribute("id"));
                return SUCCESS;
        }
        
        public String getMemberBookRecord() {
                HttpServletRequest request = ServletActionContext.getRequest();
                HttpSession session = request.getSession();
                if (session == null) {
                        return "login";
                }
                
                bookRecords = member.getBookRecords((String)session.getAttribute("id"));
                return SUCCESS;
        }
        
        public String getBookCancelRecord() {
                HttpServletRequest request = ServletActionContext.getRequest();
                HttpSession session = request.getSession();
                if (session == null) {
                        return "login";
                }
                
                cancelRecords = member.getCancelRecords((String)session.getAttribute("id"));
                return SUCCESS;
        }

        public Member getMember() {
                return member;
        }

        public void setMember(Member member) {
                this.member = member;
        }

        public List<Map<String, Integer>> getData() {
                return data;
        }

        public void setData(List<Map<String, Integer>> data) {
                this.data = data;
        }

        public MemberInfoVO getMemberInfo() {
                return memberInfo;
        }

        public void setMemberInfo(MemberInfoVO memberInfo) {
                this.memberInfo = memberInfo;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getPhone() {
                return phone;
        }

        public void setPhone(String phone) {
                this.phone = phone;
        }

        public ResultVO getResult() {
                return result;
        }

        public void setResult(ResultVO result) {
                this.result = result;
        }

        public List<ResideRecordVO> getResideRecords() {
                return resideRecords;
        }

        public void setResideRecords(List<ResideRecordVO> resideRecords) {
                this.resideRecords = resideRecords;
        }

        public List<BookRecordVO> getBookRecords() {
                return bookRecords;
        }

        public void setBookRecords(List<BookRecordVO> bookRecords) {
                this.bookRecords = bookRecords;
        }

        public List<CancelRecordVO> getCancelRecords() {
                return cancelRecords;
        }

        public void setCancelRecords(List<CancelRecordVO> cancelRecords) {
                this.cancelRecords = cancelRecords;
        }

        public Map<String, Integer> getConsumeData() {
                return consumeData;
        }

        public void setConsumeData(Map<String, Integer> consumeData) {
                this.consumeData = consumeData;
        }
        
}
