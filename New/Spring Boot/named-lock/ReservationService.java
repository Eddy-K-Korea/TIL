package com.example.reserve.damo.service;

import com.example.reserve.damo.dto.EmpOfcUseDto;
import com.example.reserve.damo.dto.OfficeSeatDto;
import com.example.reserve.damo.dto.ResponseDto;
import com.example.reserve.damo.entity.EmpOfcUse;
import com.example.reserve.damo.entity.pk.EmpOfcUsePk;
import com.example.reserve.damo.enums.ErrorCode;
import com.example.reserve.damo.enums.ResultStatus;
import com.example.reserve.damo.enums.UseYn;
import com.example.reserve.damo.lock.UserLevelLockFinal;
import com.example.reserve.damo.repository.EmpOfcUseRepo;
import com.example.reserve.damo.repository.queryDSL.EmpOfcUseQuery;
import java.time.LocalDate;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReservationService implements Reservation {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ReservationService.class);
    private final EmpOfcUseRepo empOfcUseRepo;
    private final EmpOfcUseQuery empOfcUseQuery;
    private final UserLevelLockFinal userLevelLockFinal;
    private final OfficeSeatService officeSeatService;
    private final int RESERVE_AVAILABLE_COUNT = 1;

    public ReservationService(
        EmpOfcUseRepo empOfcUseRepo, EmpOfcUseQuery empOfcUseQuery, UserLevelLockFinal userLevelLockFinal,
        OfficeSeatService officeSeatService
    ) {
        this.empOfcUseRepo = empOfcUseRepo;
        this.empOfcUseQuery = empOfcUseQuery;
        this.userLevelLockFinal = userLevelLockFinal;
        this.officeSeatService = officeSeatService;
    }

    public ResponseDto<EmpOfcUseDto> reserveOfficeSeat(EmpOfcUseDto empOfcUseDto) {
        try {
            long employeeId = Long.parseLong(empOfcUseDto.getEmployeeId());
            long officeSeatId = Long.parseLong(empOfcUseDto.getOfficeSeatId());
            LocalDate nowDate = LocalDate.now();

            //사무실 좌석 validation
            OfficeSeatDto officeSeatDto = officeSeatService.findOfficeSeat(officeSeatId);

            //하루 1번 이상 예약 여부 확인
            long reserveCount = empOfcUseQuery.countEmpOfcUse(employeeId, officeSeatId, nowDate);

            return checkAvailableReserve(reserveCount, officeSeatDto.getOfficeSeatId(), employeeId, nowDate);

        } catch (Exception ex) {
            log.error("", ex);
            throw ex;
        }
    }

    public ResponseDto<EmpOfcUseDto> checkAvailableReserve(
        long reserveCount, long officeSeatId, long employeeId, LocalDate nowDate
    ) {
        //1번 이상일 경우 에러 응답
        if (reserveCount >= RESERVE_AVAILABLE_COUNT) {
            return ResponseDto.<EmpOfcUseDto>builder()
                              .result(ResultStatus.E)
                              .resultMessage(ResultStatus.E.getStatus())
                              .code(400)
                              .errorMessage(ErrorCode.AVAILABLE_RESERVE_OVER_TIME.getMessage())
                              .build();
        } else {
            EmpOfcUsePk empOfcUsePk = EmpOfcUsePk.builder().officeSeatId(officeSeatId).reserveSeatDate(nowDate).build();
            EmpOfcUse empOfcUse = EmpOfcUse.builder()
                                           .empOfcUsePk(empOfcUsePk)
                                           .employeeId(employeeId)
                                           .useYn(UseYn.Y.getValue())
                                           .build();

            userLevelLockFinal.executeWithLock(String.valueOf(officeSeatId), 300, () -> empOfcUseRepo.save(empOfcUse));
            return ResponseDto.<EmpOfcUseDto>builder()
                              .result(ResultStatus.S)
                              .resultMessage(ResultStatus.S.getStatus())
                              .code(201)
                              .data(new EmpOfcUseDto(empOfcUse))
                              .build();

        }
    }

    public ResponseDto<EmpOfcUseDto> cancelReserve(EmpOfcUseDto empOfcUseDto) {
        try {
            long employeeId = Long.parseLong(empOfcUseDto.getEmployeeId());
            LocalDate date = empOfcUseDto.getReserveSeatDate();
            long officeSeatId = Long.parseLong(empOfcUseDto.getOfficeSeatId());
            EmpOfcUse empOfcUse = empOfcUseQuery.findEmpOfcUse(employeeId, officeSeatId, date, UseYn.Y.getValue())
                                                .orElseThrow();
            empOfcUse.updateUseYn(UseYn.N);

            return ResponseDto.<EmpOfcUseDto>builder()
                              .result(ResultStatus.S)
                              .resultMessage(ResultStatus.S.getStatus())
                              .code(200)
                              .data(new EmpOfcUseDto(empOfcUse))
                              .build();
        } catch (Exception ex) {
            log.error("", ex);
            throw ex;
        }
    }
}
