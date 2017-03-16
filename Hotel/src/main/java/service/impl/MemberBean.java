package service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dao.intf.MemberDAO;
import po.hotel.BookRecordPO;
import po.hotel.ResideRecordPO;
import po.member.MemberPO;
import service.intf.Member;
import vo.member.ActivateVO;
import vo.member.BookRecordVO;
import vo.member.CancelRecordVO;
import vo.member.CancelVO;
import vo.member.MemberInfoVO;
import vo.member.ModifyInfoVO;
import vo.member.RechargeVO;
import vo.member.RegisterVO;
import vo.member.ResideRecordVO;
import vo.result.ResultVO;

public class MemberBean implements Member {
        
        private MemberDAO member;

        @Override
        public ResultVO register(RegisterVO vo) {
                boolean isExist = member.isMemberExist(vo.getIdCard());
                if (isExist) {
                        return new ResultVO(false, "您已经申请过会员卡");
                }
                return member.registerMember(vo.toPO());
        }

        public void setMember(MemberDAO member) {
                this.member = member;
        }

        @Override
        public ResultVO activate(ActivateVO vo) {
                boolean isBankValid = this.member.isBankAccountValid(
                                vo.getAccountId(), vo.getAccountPw()
                );
                if (!isBankValid) {
                        return new ResultVO(false, "银行账号与密码不匹配");
                }
                
                int balance = this.member.getAccountBalance(vo.getAccountId());
                if (balance < vo.getMoney()) {
                        return new ResultVO(false, "银行账户余额不足");
                }
                
                return this.member.activateMember(vo);
        }

        @Override
        public ResultVO cancel(CancelVO vo) {
                boolean isExist = this.member.isAccountExist(vo.getAccountId());
                if (!isExist) {
                        return new ResultVO(false, "该银行账户不存在");
                }
                return this.member.cancelMember(vo);
        }

        @Override
        public ResultVO recharge(RechargeVO vo) {
                boolean isValid = this.member.isBankAccountValid(
                                vo.getAccountId(), vo.getPassword()
                );
                if (!isValid) {
                        return new ResultVO(false, "银行账号与密码不匹配");
                }
                
                int balance = this.member.getAccountBalance(vo.getAccountId());
                if (balance < vo.getMoney()) {
                        return new ResultVO(false, "银行卡余额不足");
                }
                
                return this.member.rechargeMember(vo);
        }

        @Override
        public List<Map<String, Integer>> getMemberData(String memberId) {
                List<Map<String, Integer>> result = new ArrayList<Map<String,Integer>>();
                result.add(member.getResideData(memberId));
                result.add(member.getBookData(memberId));
                result.add(member.getCancelData(memberId));
                return result;
        }

        @Override
        public MemberInfoVO getBasicInfo(String memberId) {
                MemberPO po = member.getBasicInfo(memberId);
                return po.toVO();
        }

        @Override
        public ResultVO modifyInfo(ModifyInfoVO vo) {
                return member.modifyInfo(vo);
        }

        @Override
        public List<ResideRecordVO> getResideRecords(String memberId) {
                List<ResideRecordPO> records = member.getResideRecords(memberId);
                List<ResideRecordVO> result = new ArrayList<>();
                for (ResideRecordPO po : records) {
                        result.add(po.toVO());
                }
                return result;
        }

        @Override
        public List<BookRecordVO> getBookRecords(String memberId) {
                List<BookRecordPO> records = member.getBookRecords(memberId);
                List<BookRecordVO> result = new ArrayList<>();
                for (BookRecordPO po : records) {
                        result.add(po.toVO());
                }
                return result;
        }

        @Override
        public List<CancelRecordVO> getCancelRecords(String memberId) {
                return member.getCancelRecords(memberId);
        }

        @Override
        public Map<String, Integer> getCosumeStat(String memberId) {
                return member.getCosumeStat(memberId);
        }

}
