
const Modal = ({modalRef, hideEvent, asynEvent, modalTitle}) => {
  return (
    <div className="modal fade" ref={modalRef} tabIndex="-1">
      <div className="modal-dialog">
        <div className="modal-content">
          <div className="modal-header">
            <h1 className="modal-title fs-5">{modalTitle}</h1>
            <button type="button" className="btn-close" onClick={hideEvent}></button>
          </div>
          {/* <div className="modal-body"></div> */}
          <div className="modal-footer d-flex">
            <button type="button" className="btn btn-primary flex-fill" onClick={asynEvent}>확인</button>
            <button type="button" className="btn btn-danger flex-fill" onClick={hideEvent}>취소</button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Modal;