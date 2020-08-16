import React from 'react';
import ReactDOMServer from 'react-dom/server';
import Convert from 'ansi-to-html';

const convert = new Convert();

const Style: React.FC<{}> = () => {
  return (
    <style>{`
    body {
      margin: 0;
      background-color: #24292e;
    }

    li > span {
      color: #959da5;
      font-weight: normal;
      padding-right: 7.5px;
    }

    p {
      margin: 0;
      color: #f6f8fa;
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
    <li>
      <span>{time}</span>
      <p>{html}</p>
    </li>
  );
};

export const renderLogs = (logs: Log[]): string => {
  return ReactDOMServer.renderToStaticMarkup(
    <>
      <Style />
      <ul>
        {logs.map((log) => (
          <Log key={log.timestamp} log={log} />
        ))}
      </ul>
    </>,
  );
};
