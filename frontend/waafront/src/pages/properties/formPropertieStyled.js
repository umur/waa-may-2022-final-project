import React, { useState } from "react";
import { useRef } from "react";
import { statesOptions } from "./statesOptions";
import {
  Form,
  Input,
  Button,
  Radio,
  Select,
  Cascader,
  DatePicker,
  InputNumber,
  TreeSelect,
  Switch,
} from "antd";

const FormSizeDemo = () => {
  const [componentSize, setComponentSize] = useState("default");
  const nameRef = useRef(null);
  const onFormLayoutChange = ({ size }) => {
    setComponentSize(size);
  };

  const myFunction = function () {
    alert("teste");
  };

  return (
    <Form
      labelCol={{
        span: 4,
      }}
      wrapperCol={{
        span: 14,
      }}
      layout="horizontal"
      initialValues={{
        size: componentSize,
      }}
      onValuesChange={onFormLayoutChange}
      size={componentSize}
      onsubmit="myFunction()"
    >
      <Form.Item label="Form Size" name="size">
        <Radio.Group>
          <Radio.Button value="small">Small</Radio.Button>
          <Radio.Button value="default">Default</Radio.Button>
          <Radio.Button value="large">Large</Radio.Button>
        </Radio.Group>
      </Form.Item>
      <Form.Item label="Name">
        <Input ref={nameRef} name="name" />
      </Form.Item>
      <Form.Item label="Street">
        <Input name="street" />
      </Form.Item>
      <Form.Item label="City">
        <Input name="city" />
      </Form.Item>
      <Form.Item label="States">
        <Select style={{ width: "50%" }}>
          <Select.Option value=""></Select.Option>
          {statesOptions.map((statesOption) => (
            <option value={statesOption.value}>{statesOption.title}</option>
          ))}
        </Select>
      </Form.Item>
      <Form.Item label="Zip Code">
        <Input name="zipcode" style={{ width: "30%" }} />
      </Form.Item>

      <Form.Item label="Number of Bedrooms">
        <InputNumber name="nbedrooms" style={{ width: "30%" }} />
      </Form.Item>
      <Form.Item label="Number of Bathrooms">
        <InputNumber name="nbathrooms" style={{ width: "30%" }} />
      </Form.Item>

      <Form.Item label="Rent Amount">
        <Input name="rentamount" style={{ width: "30%" }} />
      </Form.Item>
      <Form.Item label="Security Deposit">
        <Input name="security" style={{ width: "30%" }} />
      </Form.Item>

      <Form.Item label="Occupied" valuePropName="checked">
        <Switch />
      </Form.Item>
      <Form.Item label="Listed" valuePropName="checked">
        <Switch />
      </Form.Item>
      {/* <Form.Item label="Button">
        <Button>Button</Button>
      </Form.Item> */}
    </Form>
  );
};

export default () => <FormSizeDemo />;
