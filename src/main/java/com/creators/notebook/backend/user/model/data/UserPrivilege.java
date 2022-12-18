package com.creators.notebook.backend.user.model.data;

/**
 * User의 권한을 지정하는 Enum이다.
 * # 활용 가능한 값 : AD, T0, T1
 * AD는 admin계정임을 의미.
 * T0, T1, ... 등은 일반 유저의 티어를 의미한다.
 * 2022-12-16 기준 T0 만 활용한다.
 */
public enum UserPrivilege {
  AD,T0,T1,
}
