import React from 'react'
import BreadCrumb from '../../components/BreadCrumb'

const LandLordList = () => {
    return (
        <div className="content-wrapper">
           <BreadCrumb name='Landlords' />
            <section className="content">
                <div className="container-fluid">
                    <div className="row">
                        <div className="col-12">
                            <div className="card">
                                <div className="card-header">
                                    <h3 className="card-title">DataTable for Landlord</h3>
                                </div>
                                <div className="card-body">
                                    <table id="example2" className="table table-bordered table-hover">
                                        <thead>
                                            <tr>
                                                <th>First Name</th>
                                                <th>Last Name</th>
                                                <th>Email</th>
                                                <th>Active</th>
                                                <th>Actions</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>Lal</td>
                                                <td>Chandan</td>
                                                <td>lanchandan@miu.edu</td>
                                                <td>Yes</td>
                                                <td>
                                                    <i className='fas fa-edit text-primary'></i>
                                                    <i className='fas fa-trash text-danger ml-2'></i>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>

    )
}

export default LandLordList