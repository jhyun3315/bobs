import React, { Component } from "react"
import axios from 'axios'
// import { jsonLocalStorage } from "aws-sdk/clients/autoscaling"
import './css/StudyComment.css'


class CommunityCommentList extends Component {
  state = {
    value : '',
    update: null
  }

  cancleUpdate = e => {
    this.setState({
      ...this.state,
      update:null
    })
  }

  deleteList = k => {
    const { updateList, list } = this.props
    const newList = [...list].filter((v) => v.study_comment_id !== k)

    let data =  {
      "user_id" : 2,
      "study_comment_id" : k,
    }
    const config = {"Content-Type": 'application/json'};
    axios.delete("https://i8b304.p.ssafy.io/api/study/comment",{
      data : data,
      headers : config
    })
    .then((res) => console.log(res.data))
    .catch((err) => console.log(err))

    updateList(newList)
  }

  handleClick = i => e => {
    this.setState({
      ...this.state,
      value : i.study_comment_content,
      update : i.study_comment_id
    })
  }

  handleChange = e => {
    const { value } = { ...e.target }

    this.setState({
      ...this.state,
      value,
    })
  }

  updateKeyDown = k => e => {
    console.log(e)
    if (e.key !== 'Enter') return

    const { updateList, list } = this.props 

    const newList = [...list]
    let a = null
    newList?.map((m, index) => {
      if(m.study_comment_id === k){
        a = index  
      }
    })
    newList[a].study_comment_content = this.state.value
    let data =  {
      "user_id" : 2,
      "study_comment_id" : k,
      "study_comment_content" : this.state.value
    }
    const config = {"Content-Type": 'application/json'};
    axios.put("https://i8b304.p.ssafy.io/api/study/comment",data, config)
    .then((res) => console.log(res.data))
    .catch((err) => console.log(err))

    this.setState({
      ...this.state,
      update:null
    })
    updateList(newList)
  }

  updateClick = k => {
    const { updateList, list } = this.props 

    const newList = [...list]
    let a = null
    newList?.map((m, index) => {
      if(m.study_comment_id === k){
        a = index  
      }
    })
    newList[a].study_comment_content = this.state.value
    let data =  {
      "user_id" : 2,
      "study_comment_id" : k,
      "study_comment_content" : this.state.value
    }
    const config = {"Content-Type": 'application/json'};
    axios.put("https://i8b304.p.ssafy.io/api/study/comment",data, config)
    .then((res) => console.log(res.data))
    .catch((err) => console.log(err))

    this.setState({
      ...this.state,
      update:null
    })
    updateList(newList)
  }

  rendList = () => this.props.list?.map((m) => {
    const owner = 2
    // const owner = jsonLocalStorage.getItem("id");
    if(owner === m.user_id)
    return(
      <div className="com_cmt_ownerrow" key = {m?.study_comment_id} >
        <div className="com_cmt_ownerprofile">
          <div className="com_cmt_ownername">{m.user_name}</div>
          <div className="com_cmt_ownerimg"><img src={m.user_profile} alt="" className="com_cmt_img" /></div>
        </div>
        {
          this.state.update ===  m.study_comment_id?
          <>
          <input
            type="text"
            value={this.state.value}
            onChange={this.handleChange}
            onKeyDown={this.updateKeyDown(m.study_comment_id)}
            className='comment-update-ownerinput' />
            <div className="com_cmt_ownerbtn">
              <button onClick={this.cancleUpdate} className="com_cmt_cancle">취소</button>
              <button className="com_cmt_complete" onClick={() => {this.updateClick(m.study_comment_id)}}>완료</button>
            </div> </>:
            <>
              <div className="com_cmt_ownercontent">{m.study_comment_content}</div>
              <div className="com_cmt_ownerbtn">
                <button onClick={this.handleClick(m)} className="com_cmt_edit">수정</button>
                <button className="com_cmt_delete" onClick={() => {this.deleteList(m.study_comment_id)}}>삭제</button>
              </div>
            </>
        }
      </div>
    )
    else
    return(
      <div className="com_cmt_row" key = {m?.study_comment_id} >
        <div className="com_cmt_profile">
          <div className="com_cmt_img"><img src={m.user_profile} alt="" className="com_cmt_img" /></div>
          <div className="com_cmt_name">{m.user_name}</div>
        </div>
        <div className="com_cmt_content">{m.study_comment_content}</div>
      </div>
    )
  })


  render() {
    return (
      <div className="com_cmt_chatlist">
        {this.rendList()}
      </div>
    )
  }   
}

export default CommunityCommentList