import React from "react";
import styled from "styled-components";

export const CommonCouponWrapper = styled.div`
  position: relative;
  display: flex;
  flex-direction: column;
  width: 345px;
  height: 140px;
  padding: 20px 15px 15px;
  box-sizing: border-box;
  background-color: #fff;
  border: 1px solid ${(props) => props.theme.gray200};
`;

export const CouponContainer = styled.div`
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  grid-auto-flow: column;
`;

export const CouponWrapper = styled.div`
  padding: 10px;
`;
export const ListWrapper = styled.div`
  display: flex;
  flex-wrap: wrap;
  border-bottom: 1px solid ${(props) => props.theme.gray400};
  margin: 0 -5px;

  ${CommonCouponWrapper} & {
    margin: 0 5px 10px;
  }

  &.sale {
    padding: 4px 15px 160px;
  }

  &.search-coupon {
    padding: 0 0 160px 0;
  }

  &__noresult {
    width: 100%;
    height: 70px;
    line-height: 70px;
    font-size: 14px;
    color: ${(props) => props.theme.gray600};
    text-align: center;
  }
`;

export const CouponTitle = styled.div`
  overflow: hidden;
  width: calc(100% - 70px);
  text-align: center;
  max-height: 40px;
  margin-bottom: 9px;
  font-size: 14px;
  font-weight: bold;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  padding-top: 1px;
`;

export const CouponLabel = styled.div`
  display: inline-block;
  height: 30px; /* Adjust the height to your desired value */
  margin-top: -1px;
  padding: 0 12px; /* Adjust the padding to your desired value */
  color: ${(props) => props.theme.musinsaAccent};
  line-height: 30px; /* Adjust the line-height to your desired value */
  font-size: 14px; /* Adjust the font-size to your desired value */
  font-weight: normal;
  font-family: ${(props) => props.theme.fontMusinsa};
  white-space: nowrap;
  border: 1px solid blue; // 추가: 테두리를 파란색으로 설정
  background-color: #f3faff;
  border-radius: 2px;
  vertical-align: bottom;

  &:first-of-type {
    margin-right: 2px;
  }
  &:last-of-type {
    margin-right: 6px;
  }
  &--app {
    color: ${(props) => props.theme.gray800};
    background-color: ${(props) => props.theme.gray100};
  }
  &--direct {
    color: ${(props) => props.theme.blue400};
    border: 1px solid ${(props) => props.theme.blue400}; /* Add blue border */
  }
`;
export const InformationWrapper = styled.div`
  display: flex;
  flex: 1;
  justify-content: space-between;
  flex-direction: row;
`;

export const BottomWrapper = styled.div`
  display: flex;
`;

export const PeriodWrapper = styled.div`
  display: flex;
  font-size: 12px;
  font-family: ${(props) => props.theme.fontMusinsa};
  white-space: nowrap;

  em {
    margin-right: 5px;
    color: ${(props) => props.theme.orange1};
  }
  span {
    position: relative;
    padding-right: 6px;
    margin-right: 6px;
    color: ${(props) => props.theme.gray700};

    &::after {
      position: absolute;
      right: 0;
      top: 1px;
      display: block;
      width: 1px;
      height: 11px;
      background-color: ${(props) => props.theme.gray300};
      content: "";
    }

    &:last-of-type {
      padding-right: 0;
      margin-right: 0;
      &::after {
        display: none;
      }
    }
  }
`;

export const DiscountWrapper = styled.div`
  display: flex;
  flex-wrap: wrap;
  flex: 1;

  &__rate {
    margin-right: 6px;
    color: ${(props) => props.theme.musinsaAccent};
    font-weight: 400;
    font-size: 28px;
    line-height: 100%;
    font-family: ${(props) => props.theme.fontMusinsa};
  }
`;

export const DiscountInformationWrapper = styled.div`
  display: flex;
  align-items: flex-end;
  padding-top: 5px;
  margin-bottom: 5px;
  color: ${(props) => props.theme.gray500};
  font-size: 12px;
  font-family: ${(props) => props.theme.fontMusinsa};

  em {
    margin-right: 4px;
    color: ${(props) => props.theme.musinsaAccent};
  }
`;

export const ButtonWrapper = styled.div`
  &s {
    display: flex;
    align-items: flex-end;
    margin: 0 0 3px auto;
  }

  position: relative;
  height: 22px;
  margin-left: 6px;
  padding: 0 8px;
  line-height: 22px;
  font-size: 12px;
  background-color: ${(props) => props.theme.white};
  border: 1px solid ${(props) => props.theme.gray300};
  border-radius: 4px;
  box-sizing: border-box;
  z-index: 0;

  > a {
    position: absolute;
    left: 0;
    top: 0;
    bottom: 0;
    right: 0;
  }
  &:first-child {
    z-index: 1;
  }
  &:last-of-type {
    position: static;
  }
  &--not-applicable {
    color: ${(props) => props.theme.gray500};
  }
  &--download {
    color: ${(props) => props.theme.white};
    background-color: ${(props) => props.theme.black};
    border-color: ${(props) => props.theme.black};
  }
`;

export const LogoWrapper = styled.div`
  position: absolute;
  top: 20px;
  left: 15px;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 50px;
  height: 50px;
  border: 1px solid ${(props) => props.theme.gray300};
  border-radius: 100%;
  box-sizing: border-box;

  > img {
    max-width: 70%;
    max-height: 50%;
  }
`;

export const CouponCreateForm = styled.form`
  label {
    display: block;
    margin-bottom: 10px;
  }

  input {
    margin-top: 5px;
  }

  button {
    margin-top: 10px;
  }
`;
