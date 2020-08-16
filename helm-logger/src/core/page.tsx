import React from 'react';
import ReactDOMServer from 'react-dom/server';
import Convert from 'ansi-to-html';

const convert = new Convert();

const Style: React.FC<{}> = () => {
  return (
    <style>{`
    body {
      background-color:#24292e;
    }
    b {
      color:#959da5;
      font-weight:normal;
    }
    pre {
      margin:0;
      color:#f6f8fa;
    }`}</style>
  );
};

interface LogProps {
  log: Log;
}

const Log: React.FC<LogProps> = (props: LogProps) => {
  const { text, timestamp } = props.log;
  const html = convert.toHtml(text);
  const time = timestamp.padEnd(30, ' ');
  return (
    <pre>
      <b>{time}</b>
      {html}
    </pre>
  );
};

export const renderLogs = (logs: Log[]): string => {
  return ReactDOMServer.renderToStaticMarkup(
    <>
      <Style />
      {logs.map((log) => (
        <Log key={log.timestamp} log={log} />
      ))}
    </>,
  );
};
