import React from 'react'

const BreadCrumb = (props) => {
  return (
    <section className="content-header">
    <div className="container-fluid">
        <div className="row mb-2">
            <div className="col-sm-6">
                <h1>{props.name}</h1>
            </div>
            <div className="col-sm-6">
                <ol className="breadcrumb float-sm-right">
                    <li className="breadcrumb-item">Home</li>
                    <li className="breadcrumb-item active">{props.name}</li>
                </ol>
            </div>
        </div>
    </div>
</section>
  )
}

export default BreadCrumb