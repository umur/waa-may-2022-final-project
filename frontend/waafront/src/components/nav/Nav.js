import { Layout, Menu } from 'antd';
import './nav.css'
import { Link } from "react-router-dom";

const { Header } = Layout;

const navWithoutLogin = [
    {key: 1, label: `Login`},
    {key: 2, label: `Register`},
    {key: 0, label: `Register`},
];
function getMenu(role) {
    if(role == ''){
        return (
            <>
                {/* <Menu.Item><a href="/#" className='hide-element'>Random</a></Menu.Item> */}
                 <Menu.Item >
                    <Link to='/properties'>About</Link>
                </Menu.Item>
                <Menu.Item >
                    <Link to='/properties'>Support</Link>
                </Menu.Item>
                <Menu.Item>
                    <Link to='/users'>Help</Link>
                </Menu.Item> 
                
                 <Menu.Item>
                    <Link to='/users'>Logout</Link>
                </Menu.Item>
                <Menu.Item>
                    <Link to='/login'>Login</Link>
                </Menu.Item>
                
                
            </>
        )
    }
    else if(role.toLowerCase() === 'landlord'){
        return (
            <>
                {/* <Menu.Item><a href="/#" className='hide-element'>Random</a></Menu.Item> */}
                <Menu.Item >
                    <Link to='/users'>Landlord link 1</Link>
                </Menu.Item>
                <Menu.Item>
                    <Link to='/users'>Landlord link 2</Link>
                </Menu.Item>
                <Menu.Item>
                    <Link to='/users'>Logout</Link>
                </Menu.Item>
            </>
        )
    }
    else if(role.toLowerCase() === 'tenant'){
        return (
            <>
                {/* <Menu.Item><a href="/#" className='hide-element'>Random</a></Menu.Item> */}
                <Menu.Item >
                    <Link to='/users'>Tenant link 1</Link>
                </Menu.Item>
                <Menu.Item>
                    <Link to='/users'>Tenant link 2</Link>
                </Menu.Item>
                <Menu.Item>
                    <Link to='/users'>Logout</Link>
                </Menu.Item>
            </>
        )
    }
    else if(role.toLowerCase() === 'admin'){
        return (
            <>
                {/* <Menu.Item><a href="/#" className='hide-element'>Random</a></Menu.Item> */}
                <Menu.Item >
                    <Link to='/properties'>Properties</Link>
                </Menu.Item>
                <Menu.Item>
                    <Link to='/users'>Userss</Link>
                </Menu.Item>
                {/* <Menu.Item>
                    <Link to='/register'>Register</Link>
                </Menu.Item> */}
                {/* <Menu.Item>
                    <Link to='/login'>Login</Link>
                </Menu.Item> */}
            </>
        )
    }
        
    
}

function Nav(props) {
    const rightStyle = {position: 'absolute', top: 0, right: 30} 

    return (
        <Header className="header">
            <div className="logo"><Link to='/'>Home</Link></div>
          
            <Menu theme="dark" mode="horizontal" style={rightStyle}>
                { getMenu(props.role) }
            </Menu>
         
        </Header>
    )
}

export default  Nav;